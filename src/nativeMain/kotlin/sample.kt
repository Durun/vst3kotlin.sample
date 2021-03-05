import io.github.durun.data.Vec2
import io.github.durun.io.Path
import io.github.durun.resource.use
import io.github.durun.vst3kotlin.cppinterface.HostCallback
import io.github.durun.vst3kotlin.hosting.VstClassRepository
import io.github.durun.vst3kotlin.hosting.VstFile
import io.github.durun.vst3kotlin.pluginterface.base.VstClassCategory
import io.github.durun.window.Window
import io.github.durun.window.WindowEvent

/**
 * VSTプラグイン画面を開き、操作画面から送られたメッセージを標準出力するサンプルです。
 * 無操作状態がしばらく続くと終了します
 */
fun main(args: Array<String>) {
    val path = Path.of(args.last())
    println("Opening $path")

    val repo: VstClassRepository = VstFile.of(path)
    val classId =
        repo.classInfos.find { it.category == VstClassCategory.AudioEffect }?.classId ?: error("VST classID not found")
    val vstClass = repo[classId] ?: error("VST class not found")

    val window = Window.create(size = Vec2(480, 320), "VST3 Plugin")

    vstClass.createControllerInstance().use { controllerInstance ->
        controllerInstance.plugView?.attached(window)
        window.show()
        println("Showed window.")
        var noTouchCount = 0
        window.loop {
            HostCallback.receiveMessages().forEach {
                println("Received message: $it")
                noTouchCount = 0
            }
            val continueFlag: Boolean = noTouchCount < 1000 && it !is WindowEvent.OnClosed
            noTouchCount++
            continueFlag
        }
    }
    println("Exit.")
}