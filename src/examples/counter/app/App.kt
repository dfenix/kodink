package demo.app

import demo.components.counter
import demo.reducers.CounterReducer
import demo.reducers.CounterState
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.stage.Stage
import redux.Action
import redux.Provider

class App : Application() {
    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {

        Provider.createStore(CounterReducer())
        val counter = counter {
            value = (Provider.store.getState() as CounterState).counter
            onIncrement = { Provider.store.dispatch(Action("INCREMENT", CounterState())) }
            onDecrement = { Provider.store.dispatch(Action("DECREMENT", CounterState())) }
        }
        Provider.store.subscribe { counter.value = (Provider.store.getState() as CounterState).counter }

        val root = VBox()
        root.children.add(counter)
        primaryStage.title = "Redux demo"
        primaryStage.scene = Scene(root, 300.0, 275.0)

        primaryStage.show()
    }
}