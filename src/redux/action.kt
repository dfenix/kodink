package redux

class Action<S>(val type: String, val value: S)

enum class ActionTypes(type: String) {
    INIT("@@redux/INIT")
}