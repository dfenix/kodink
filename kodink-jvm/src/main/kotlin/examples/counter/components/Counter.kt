package examples.counter.components

import ui.Component
import ui.container

class Counter : Component() {
    /*lateinit var textReference: Text
    var value: Int = 0
        set(value) {
            textReference.text = "Clicked: $value times"
        }

    lateinit var onIncrement: () -> Unit
    lateinit var onDecrement: () -> Unit*/

    override fun render() = container {
        text {
            +"Clicked: ${props["value"]} times"
        }
        button {
            setOnAction { props["onIncrement"] }
            +"+"
        }
        button {
            setOnAction { props["onDecrement"] }
            +"-"
        }
    }
}