package examples.shoppingCart.reducers

import redux.Provider.store

fun combineReducers(){
    store.addReducer(::cartReducer, InitialState())
    store.addReducer(::productsReducer, InitialState())

}