//package examples.shoppingCart
//
//abstract class KComp{
//    internal val children = mutableListOf<KComp>()
//
////    init {
////        children.add(render())
////    }
////
////    abstract fun render(): KComp
//
//    protected fun <T : KComp> initComponent(comp: T, init: T.() -> Unit): T {
//        comp.init()
//        children.add(comp)
//        return comp
//    }
//
//    fun text(init: Text.() -> Unit) = initComponent(Text(), init)
//}
//
//class Text: KComp(){
//    var text: String = ""
//    override fun render(): KComp {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
//class KApp(val root: KComp)
//
//class AppBuilder{
//    private lateinit var root: KComp
//
////    fun container(init: Container.() -> Unit) {
////        val container = Container()
////        container.init()
////        root = container
////    }
//
//    fun container(children: MutableList<KComp>): Container{
//        val container = Container()
//        container.children += children
//        return container
//    }
//
//    fun build(): KApp{
//        return KApp(root)
//    }
//}
//
//fun app(init: AppBuilder.() -> Unit): KApp{
//    val app = AppBuilder()
//    app.init()
//    return app.build()
//}
//
//class Container: KComp(){
//    override fun render(): KComp {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}