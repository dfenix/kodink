package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage

class App: Application(){
    private val root: VBox = VBox()

    override fun start(primaryStage: Stage?) {
        primaryStage?.title = "Kodink demo"
        primaryStage?.scene = Scene(root, 300.0, 275.0)
        primaryStage?.show()
    }

    fun text(setup: Text.() -> Unit): Text {
        val text = Text()
        text.setup()
        root.children.add(text)
        return text
    }
}

class RunApplication: Application() {
    companion object {
        var app: App? = null
    }

    override fun start(primaryStage: Stage?) {
        app?.start(primaryStage)
    }
}

fun runApp(app: App){
    RunApplication.app = app
    Application.launch(RunApplication::class.java)
}

fun app(setup: App.() -> Unit): App{
    val app = App()
    app.setup()
    return app
}

