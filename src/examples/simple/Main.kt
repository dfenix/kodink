package examples.simple

import javafx.scene.Parent
import ui.RunApplication
import ui.container
import ui.runApp

fun main(args: Array<String>) {
    basicExample()
    runApp(Launch::class)
}

class Launch: RunApplication(){
    override fun render(): Parent {
        return container {
            text {
                +"Hello World!"
            }
            text {
                +"Another World!"
            }
            button {
                +"Do something!"
            }
        }
    }
}