package examples.counter.app

import examples.counter.components.Counter
import examples.counter.reducers.CounterReducer
import examples.counter.reducers.Decrement
import examples.counter.reducers.Increment
import redux.Provider
import redux.Provider.store
import redux.toState
import ui.Application
import ui.component

class App : Application() {
    init {
        Provider.createStore(CounterReducer(), 0.toState())
        store.subscribe { update() }
        title = "Counter example"
        width = 150.0
        height = 80.0
    }

    override fun render() = component(Counter()) {
        props["value"] = { store.getState() }
        props["onIncrement"] = { store.dispatch(Increment()) }
        props["onDecrement"] = { store.dispatch(Decrement()) }
    }
}
