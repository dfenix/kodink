package ui

import javafx.scene.control.Control
import com.sun.javafx.scene.control.behavior.BehaviorBase
import com.sun.javafx.scene.control.inputmap.InputMap
import javafx.scene.control.SkinBase

open class Component : Control(){
    init{
        styleClass.add("component")
    }

    override fun getUserAgentStylesheet(): String {
        return javaClass.getResource("component.css").toExternalForm()
    }
}

class ComponentBehavior(control: Component) : BehaviorBase<Component>(control) {
    override fun getInputMap(): InputMap<Component> {
        TODO("not implemented")
    }
}

class ComponentSkin(control: Component) : SkinBase<Component>(control)