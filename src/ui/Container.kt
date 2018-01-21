package ui

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text

fun container(init: Container.() -> Unit): Container {
    val container = Container()
    container.init()
    return container
}

class Container : VBox() {
    fun text(init: Text.() -> Unit): Text {
        val text = Text()
        text.init()
        children.add(text)
        return text
    }

    fun button(init: Button.() -> Unit): Button {
        val button = Button()
        button.init()
        children.add(button)
        return button
    }

}