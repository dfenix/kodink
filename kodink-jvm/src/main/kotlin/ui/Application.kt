package ui

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.reflect.KClass

abstract class Application : Application() {
    private lateinit var root: Parent
    var title: String? = null
    var width: Double? = null
    var height: Double? = null
    override fun start(primaryStage: Stage?) {
        root = render()
        primaryStage?.title = title ?: "Kodink demo"
        primaryStage?.scene = Scene(root, width ?: 300.0, height ?: 275.0)
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

