package ui

import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.SkinBase
import javafx.scene.layout.*
import javafx.scene.paint.Color

abstract class Component : Control() {
    override fun createDefaultSkin(): Skin<*> {
        return ComponentSkin(this)
    }

    abstract fun render(): Node

    init {
        children.add(render())
    }
}

class ComponentSkin(component: Component) : SkinBase<Component>(component) {
    init {
        component.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
        component.border = Border(BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths(2.0)))
    }
}