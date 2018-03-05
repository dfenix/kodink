package examples.counter.app

import counter.components.Counter
import examples.counter.reducers.CounterState
import examples.counter.reducers.Decrement
import examples.counter.reducers.Increment
import examples.counter.reducers.counterReducer
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import redux.Provider
import redux.Provider.store
import ui.component

class App : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

//      TODO  store.addReducer(::counterReducer, "counter", CounterState())
        val counter = component(Counter()) {
            //value = (Provider.store.getStateFor(::counterReducer.name) as CounterState).counter
            onIncrement = { Provider.store.dispatch(Increment()) }
            onDecrement = { Provider.store.dispatch(Decrement()) }
        }
//      TODO  Provider.store.subscribe { counter.value = (Provider.store.getStateFor("counter") as CounterState).counter }

        val root = VBox()
        root.children.add(counter)
        primaryStage.title = "Redux demo"
        primaryStage.scene = Scene(root, 300.0, 275.0)

        primaryStage.show()
    }
}