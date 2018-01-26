package ui

import javafx.scene.text.Text

fun app(init: App.() -> Unit): App{
    val app = App()
    app.init()
    return app
}

class App{
    fun text(init: Text.() -> Unit): Text {
        val text = Text()
        text.init()
//        children.add(text)
        return text
    }
}