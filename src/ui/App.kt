package ui

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.scene.text.Text
import javafx.stage.Stage

class App: Application(){
    val root: VBox = VBox()

    override fun start(primaryStage: Stage?) {
        primaryStage?.title = "Kodink demo"
        primaryStage?.scene = Scene(root, 300.0, 275.0)
        primaryStage?.show()
    }

    fun text(setup: ComponentWithText.() -> Unit): ComponentWithText {
        val text = ComponentWithText()
        text.setup()
        root.children.add(text)
        return text
    }

    fun container(setup: Container.() -> Unit): Container {
        val container = Container()
        container.setup()
        root.children.add(container)
        return container
    }

    fun button(setup: Button.() -> Unit): Button {
        val button = Button()
        button.setup()
        root.children.add(button)
        return button
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

class ComponentWithText: Text(){
    operator fun String.unaryPlus() {
        text = this
    }
}

class ComponentWithAction: Button(){
    var onClick: () -> Unit = {}
        set(value) = setOnAction { value }
    var _disabled: Boolean
        get() = isDisabled
        set(value) = super.setDisabled(value)
    operator fun String.unaryPlus() {
        text = this
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

