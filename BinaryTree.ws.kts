import javax.xml.bind.JAXBElement

data class Node<T>(
    val value: T,
    val left: Node<T>? = null,
    val right: Node<T>? = null
)

val t1 = Node( "B",
        Node("A"),
        Node("C")
)

val t2 = Node( "D",
    Node( "B",
        Node("A"),
        Node("C")
    )
)

val t3 = Node( "D",
    Node( "B",
        Node("A"),
        Node("C")
    ),
    Node ( "F",
        Node( "E")
    )
)
fun <T> transversalPreOrder( start: Node<T>?, result: MutableList<T> ) {
    start?.let {
        result.add(it.value)
        transversalPreOrder(it.left, result)
        transversalPreOrder(it.right, result)
    }
}

fun <T> transversalInOrder( start: Node<T>?, result: MutableList<T> ){
    start?.let {
        transversalInOrder(it.left, result)
        result.add(it.value)
        transversalInOrder(it.right, result)
    }
}

fun <T> Node<T>.joinInOrder(): List<T> {
    return when {
        left == null && right == null -> listOf(this.value)
        left == null -> listOf(this.value)+right!!.joinInOrder()
        right == null -> left.joinInOrder() + this.value
        else -> left!!.joinInOrder() + this.value + right!!.joinInOrder()
    }
}

println( t1.joinInOrder() )

println( t2.joinInOrder() )

println( t3.joinInOrder() )



//fun <T> Node<T>.asFlow(): Flow<T> {
//    return flow {
//        emit( value )
//    }
//}

//launch {
//    t1.asFLow().collect{ print(it) }
//}
