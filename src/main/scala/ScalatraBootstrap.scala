

import org.scalatra._

import javax.servlet.ServletContext
import com.unitec.util.BaseUtil
import com.unitec.controller.HomeController
import com.unitec.controller.ComprasController
import com.unitec.controller.LocalController

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    BaseUtil.conection()
    context.mount(new HomeController, "/login")
    context.mount(new ComprasController, "/compras")
    context.mount(new LocalController, "/locais")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
    BaseUtil.closeDbConnection()
  }
}
