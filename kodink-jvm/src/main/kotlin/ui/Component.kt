package ui

import javafx.application.Platform
import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.SkinBase

abstract class Component : Control() {
    private var subComponents = mutableListOf<Control>()
    private val innerFunctions = mutableMapOf<String, () -> Unit>()
    val props = Properties()

    override fun createDefaultSkin(): Skin<*> {
        return ComponentSkin(this)
    }

    abstract fun render(): Node

    fun create() {
        children.add(render())
        if (children.size == 1) {
            val container = children[0]
            if (container is Container) {
                container.children.addAll(subComponents)
                container.children.filter { it is Component }.forEach { (it as Component).create() }
            }
        }
    }

    operator fun List<Control>.unaryPlus() {
        subComponents = this.toMutableList()
    }

    fun update() {
        Platform.runLater({
            children.clear()
            create()
        })
    }

    /*fun subscribe(name: String, function: () -> Unit) {
        innerFunctions[name] = function
    }

    fun function(name: String): () -> Unit {
        return innerFunctions[name]!!
    }*/
}

class ComponentSkin(component: Component) : SkinBase<Component>(component) {
    init {
        component.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
//        component.border = Border(BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths(2.0)))
    }
}

class Properties {
    private val props = mutableMapOf<String, () -> Any>()
    operator fun get(name: String) = props[name]?.invoke()

    operator fun set(name: String, value: () -> Any) {
        props[name] = value
    }
}