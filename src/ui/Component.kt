package ui

import javafx.scene.Node
import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.SkinBase
import javafx.scene.layout.*
import javafx.scene.paint.Color

abstract class Component : Control() {
    var subComponents = mutableListOf<Control>()
    override fun createDefaultSkin(): Skin<*> {
        return ComponentSkin(this)
    }

    abstract fun render(): Node

    /*init {
        children.add(render())
    }*/

    fun create(){
        children.add(render())
        if(children.size == 1){
            val container = children[0]
            if(container is Container){
                container.children.addAll(subComponents)
                container.children.filter { it is Component}.forEach { (it as Component).create() }
            }
        }
    }

    operator fun List<Control>.unaryPlus(){
        subComponents = this.toMutableList()
    }
}

class ComponentSkin(component: Component) : SkinBase<Component>(component) {
    init {
        component.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE)
        component.border = Border(BorderStroke(Color.BLUE, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths(2.0)))
    }
}