package experiments

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlin.test.Test


class FunWithTrees {
    data class Node<T>(
        val value: T,
        val left: Node<T>? = null,
        val right: Node<T>? = null
    )

    val t1 = Node("B",
        Node("A"),
        Node( "C")
    )

    val t2 = Node("D",
        Node("B",
            Node("A"),
            Node("C")
        ),
        Node("E",
            null,
            Node("F")
        )
    )

    private fun <T> Node<T>.asFlow(): Flow<T> {
        return flow {
            left?.asFlow()?.collect { emit(it) }
            emit( value )
            right?.asFlow()?.collect { emit(it) }
        }
    }

    @FlowPreview
    private fun <T> Node<T>?.asFlowOrEmpty(): Flow<T> {
        return if ( this == null)
            emptyFlow()
        else
            flowOf(
                left.asFlowOrEmpty(),
                flowOf( value ),
                right.asFlowOrEmpty()
            ).flattenConcat()
    }

    private fun <T> Node<T>.traverseInOrder( callback: (T)->Unit ) {
        left?.traverseInOrder(callback)
        callback(value)
        right?.traverseInOrder(callback)
    }

    private suspend fun <T> CoroutineScope.traverseBreadthFirst( root: Node<T>, callback: (T)->Unit ) {

        val ch = BroadcastChannel<Node<T>>(100)

        val j = launch(start = CoroutineStart.DEFAULT) {
            ch.asFlow().collect { node ->
                node.left?.let { ch.offer(it) }
                node.right?.let { ch.offer(it) }
                callback(node.value)
            }
        }
        yield()
        ch.offer(root)
        yield()
        ch.close()
        j.join()
    }

    private suspend fun <T> traverseBreadthFlowy( root: Node<T>, callback: suspend (T)->Unit ) {
        val msf = MutableSharedFlow<Node<T>?>(extraBufferCapacity = 100)  // Ugg capacity needs to anticipate the widest level

        val j = coroutineScope {
            launch {
                msf.takeWhile { it != null }.collect { n ->
                    n?.let { node ->
                        callback(node.value)
                        node.left?.let { msf.emit(it) }
                        node.right?.let { msf.emit(it) }
                    }
                }
            }
        }
        yield()
        msf.emit(root)
        yield()
        msf.emit(null)
        j.join()
    }

    private suspend fun <T> traverseBreadthToFlow( root: Node<T>): Flow<T> {
        return channelFlow {
            coroutineScope { traverseBreadthFlowy(root) { send(it) } }
        }
    }


    @Test
    fun printAsFlow() = runBlocking {
        println("-----> start")
        t1.asFlow().collect { print(it) }
        println("\n--")
        val output = mutableListOf<String>()
        t2.asFlow().collect{ output.add(it) }
        println(output.joinToString("-"))
        println("\n-----> done")

        println(t2.asFlow().toList().joinToString("-") )
    }

    @Test
    fun printAsFlow2() = runBlocking {
        println("-----> start")
        t1.asFlowOrEmpty().collect { print(it) }
        println("\n--")
        t2.asFlowOrEmpty().collect { print(it) }
        println("\n-----> done")

    }

    @Test
    fun printFunctionally() {

        println("---> Traverse functionally with print")
        t2.traverseInOrder { print(it) }
        println("")
        println("---> Traverse functionally into list")
        val ml = mutableListOf<String>()
        t2.traverseInOrder { ml.add(it) }
        println( ml.joinToString("-"))
        println("----> done")
    }

    @Test
    fun spanBreadth() = runBlocking {
        println("---> Start breadth span")
        traverseBreadthFirst(t2) { print(it) }
        println("\n---> Done")
    }

    @Test
    fun spanBreadth2() = runBlocking {
        println("---> Start breadth 2")
        traverseBreadthFlowy(t2) { print(it) }
        println("\n----> Done")
    }

    @Test
    fun spanBreadth2Flow() = runBlocking {
        println("---> Start breadth 2")
        traverseBreadthToFlow(t2).collect { print(it) }
        println("\n----> Done")
    }
}

