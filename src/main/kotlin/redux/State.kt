package redux

interface State

class States : State {
    private val props = mutableMapOf<String, Any>()

    operator fun get(name: String): Any? = props[name]

    operator fun set(name: String, value: Any) {
        props[name] = value
    }
}