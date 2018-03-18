package redux

interface State
interface BasicState : State {
    val value: Any
}

class States : State {
    private val props = mutableMapOf<String, Any>()

    operator fun get(name: String): Any? = props[name]

    operator fun set(name: String, value: Any) {
        props[name] = value
    }
}

class IntState(override val value: Int) : BasicState

fun Int.toState() = IntState(this)

class MapState(override val value: Map<*, *>) : BasicState

fun Map<*, *>.toState() = MapState(this)

class ListState(override val value: List<*>) : BasicState

fun List<*>.toState() = ListState(this)