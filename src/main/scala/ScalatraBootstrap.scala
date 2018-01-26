

import org.scalatra._

import javax.servlet.ServletContext
import com.unitec.util.BaseUtil
import com.unitec.controller.HomeController
import com.unitec.controller.ComprasController
import com.unitec.controller.LocalController
import com.unitec.controller.MembroController
import com.unitec.controller.OrcamentoController

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    BaseUtil.conection()
    context.mount(new ComprasController, "/compras/*")
    context.mount(new LocalController, "/locais/*")
    context.mount(new MembroController, "/membros/*")
    context.mount(new OrcamentoController, "/orcamentos/*") 
    context.mount(new HomeController, "/login/*")
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
    BaseUtil.closeDbConnection()
  }
}
