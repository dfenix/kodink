//package examples.shoppingCart.reducers
//
//import constants.ActionTypes
//import redux.*
//
//data class ProductsState(val inventory: Int = 0): State
//class ProductsReducer: Reducer<ProductsState>(ProductsState(), {
//    state, action ->
//    when(action.type){
//        ActionTypes.ADD_TO_CART -> state.copy(inventory = state.inventory.minus(1))
//        else -> state
//    }
//})
//
//data class ByIdState(val id: String): State
//class ByIdReducer: Reducer<ByIdState>(ByIdState(), {
//    state, action ->
//    when(action.type){
//        ActionTypes.RECEIVE_PRODUCTS -> state
//        else -> state
//    }
//})