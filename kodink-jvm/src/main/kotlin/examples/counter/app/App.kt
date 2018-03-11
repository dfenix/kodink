package examples.counter.app

import examples.counter.components.Counter
import examples.counter.reducers.CounterReducer
import examples.counter.reducers.Decrement
import examples.counter.reducers.Increment
import redux.Provider
import redux.Provider.store
import ui.Application
import ui.component

class App : Application() {
    init {
        Provider.createStore(CounterReducer(), 0)
        store.subscribe { update() }
    }

    override fun render() = component(Counter()) {
        props["value"] = { store.getState() }
        props["onIncrement"] = { store.dispatch(Increment()) }
        props["onDecrement"] = { store.dispatch(Decrement()) }
    }
}