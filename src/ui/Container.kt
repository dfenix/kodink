package ui

import javafx.scene.layout.VBox
import javafx.scene.text.Text

fun container(setup: Container.() -> Unit): Container {
    val container = Container()
    container.setup()
    return container
}

class Container : VBox() {
    fun text(setup: ComponentWithText.() -> Unit): ComponentWithText {
        val text = ComponentWithText()
        text.setup()
        children.add(text)
        return text
    }

    fun button(setup: ComponentWithAction.() -> Unit): ComponentWithAction {
        val button = ComponentWithAction()
        button.setup()
        children.add(button)
        return button
    }

    operator fun String.unaryPlus() {
        children.add(Text(this))
    }

}