

import org.scalatra._

import javax.servlet.ServletContext
import com.unitec.util.BaseUtil
import com.unitec.controller._

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    BaseUtil.conection()
    context.mount(new ComprasController, "/compras/*")
    context.mount(new LocalController, "/locais/*")
    context.mount(new MembroController, "/membros/*")
    context.mount(new OrcamentoController, "/orcamentos/*")
    context.mount(new HomeController, "/login/*")
    
    context.initParameters("org.scalatra.cors.allowedOrigins") = "*"
  }

  override def destroy(context: ServletContext) {
    super.destroy(context)
    BaseUtil.closeDbConnection()
  }
}
