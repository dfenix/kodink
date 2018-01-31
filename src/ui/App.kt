package ui

import javafx.application.Application
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import kotlin.reflect.KClass

abstract class RunApplication: Application(){
    override fun start(primaryStage: Stage?) {
        val root = render()
        primaryStage?.title = "Kodink demo"
        primaryStage?.scene = Scene(root, 300.0, 275.0)
        primaryStage?.show()
    }

    abstract fun render(): Parent
}

//class RunApplication: Application() {
//    companion object {
//        var app: App? = null
//    }
//
//    override fun start(primaryStage: Stage?) {
//        app?.start(primaryStage)
//    }
//}

fun runApp(kClass: KClass<out RunApplication>) {
    //RunApplication.app = app
    Application.launch(kClass.java)
}

//fun app(setup: App.() -> Unit): App{
//    val app = App()
//    app.setup()
//    return app
//}

