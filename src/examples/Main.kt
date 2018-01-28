package examples

import examples.simple.plainCalls
import ui.app
import ui.runApp

fun main(args: Array<String>) {
    plainCalls()
    runApp(
            app {
                text {
                    +"Hello World!"
                }
                text {
                    +"Another World!"
                }
                button{
                    text = "Do something!"
                }
            }
    )
}

/*class Launch: Application(){
    override fun start(primaryStage: Stage?) {
        val c = container {
            text { +"teasdfasdf" }
            button { +"asdfadfasdf" }
        }

        primaryStage?.title = "Kodink demo"
        primaryStage?.scene = Scene(c, 300.0, 275.0)
        primaryStage?.show()
    }

}*/