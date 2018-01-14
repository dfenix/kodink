package redux

data class MapOfStates<out S>(val stateByKey: Map<String, S>) : State