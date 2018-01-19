

import org.scalatra._

import javax.servlet.ServletContext
import com.unitec.util.BaseUtil
import com.unitec.controller.HomeController



class ScalatraBootstrap extends LifeCycle {
 

  override def init(context: ServletContext) {
     BaseUtil.conection()
      context.mount(new HomeController, "/login")
  } 

  override def destroy(context: ServletContext) {
    super.destroy(context)
    BaseUtil.closeDbConnection()
  }
}
