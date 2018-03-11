package ui

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.reflect.KClass

abstract class Application : Application() {
    private lateinit var root: Parent
    override fun start(primaryStage: Stage?) {
        root = render()
        primaryStage?.title = "Kodink demo"
        primaryStage?.scene = Scene(root, 300.0, 275.0)
        primaryStage?.show()
    }

    abstract fun render(): Parent

    fun update() {
        if (root is Component) {
            (root as Component).update()
        }
    }
}

fun run(kClass: KClass<out Application>) {
    Application.launch(kClass.java)
}

