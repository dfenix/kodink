//package reducers
//
//import constants.ActionTypes
//import redux.Reducer
//import redux.State
//
//data class InitialState(
//        val addedIds: List<String> = listOf(),
//        val quatityById: Map<String, Int> = mapOf(),
//        val productId: String = ""
//) : State
//
//class AddedIds : Reducer<InitialState>(InitialState(), { state, action ->
//    when (action.type) {
//        ActionTypes.ADD_TO_CART -> {
//            if (state.addedIds.contains(action.payload.productId)) {
//                state
//            } else {
//                state.copy(addedIds = state.addedIds.toList() + action.payload.productId)
//            }
//
//        }
//        else -> state
//    }
//})
//
//class QuantityById : Reducer<InitialState>(InitialState(), { state, action ->
//    when (action.type) {
//        ActionTypes.ADD_TO_CART -> {
//            val value = state.quatityById[action.payload.productId]?.plus(1) ?: 0
//            state.copy(quatityById = state.quatityById.toMap() + Pair(action.payload.productId, value))
//        }
//        else -> state
//    }
//})
//
//class CartReducer : Reducer<InitialState>(InitialState(), { state, action ->
//    when (action.type) {
//        ActionTypes.CHECKOUT_REQUEST -> InitialState()
//        ActionTypes.CHECKOUT_FAILURE -> action.payload.cart
//        else -> {
//            val addedids = AddedIds()
//            val quatityById = QuantityById()
//            InitialState(
//                    addedIds = addedids.execute(state, action).addedIds,
//                    quatityById = quatityById.execute(state, action).quatityById
//            )
//        }
//    }
//})