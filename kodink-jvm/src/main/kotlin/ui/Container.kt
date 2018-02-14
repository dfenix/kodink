package ui

import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text

fun container(setup: Container.() -> Unit): Container {
    val container = Container()
    container.setup()
    return container
}

fun <T : Component> component(component: T, setup: T.() -> Unit): T {
    component.setup()
    component.create()
    return component
}

class Container : VBox() {

    fun <T : Component> initComponent(component: T, setup: T.() -> Unit): T {
        component.setup()
        children.add(component)
        return component
    }

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

    fun container(setup: Container.() -> Unit): Container {
        val container = Container()
        container.setup()
        children.add(container)
        return container
    }

    fun <T : Component> component(component: T, setup: T.() -> Unit): T {
        component.setup()
        component.create()
        children.add(component)
        return component
    }

}

class ComponentWithAction : Button() {
    var onClick: () -> Unit = {}
        set(value) = setOnAction { value }
    var _disabled: Boolean
        get() = isDisabled
        set(value) = super.setDisabled(value)

    operator fun String.unaryPlus() {
        text = this
    }
}

class ComponentWithText : Text() {
    operator fun String.unaryPlus() {
        text = this
    }
}