package examples

import ui.app
import ui.runApp

fun main(args: Array<String>) {
    //plainCalls()
    runApp(
            app {
                text {
                    text = "Hello World!"
                }
            }
    )
}