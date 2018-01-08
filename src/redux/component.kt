package redux

import javafx.fxml.FXMLLoader
import javafx.scene.control.Control
import javax.script.ScriptEngineManager

open class Component : Control() {
    protected var state: State = State()
    private val engine = ScriptEngineManager()?.getEngineByExtension("kts")
    fun loadView(fileName: String) {
        val fxmlLoader = FXMLLoader(javaClass.getResource("$fileName.fxml"))
        fxmlLoader.setRoot(this)
        fxmlLoader.setController(this)
        //fxmlLoader.load()
    }

    fun eval(value: String) = engine.eval(value)!!
}