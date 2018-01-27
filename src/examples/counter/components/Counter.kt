package counter.components

import javafx.scene.text.Text
import ui.*

fun counter(init: Counter.() -> Unit): Counter {
    val counter = Counter()
    counter.init()
    return counter
}

class Counter : Component() {
    lateinit var textReference: Text
    var value: Int = 0
        set(value) {
            textReference.text = "Clicked: $value times"
        }

    lateinit var onIncrement: () -> Unit
    lateinit var onDecrement: () -> Unit

    override fun render() =
            container {
                textReference = text {
                    text = "Clicked: $value times"
                }
                button {
                    setOnAction { onIncrement() }
                    text = "+"
                }
                button {
                    setOnAction { onDecrement() }
                    text = "-"
                }
            }
}