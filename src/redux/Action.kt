package redux

class Action<out S>(val type: String, val value: S)