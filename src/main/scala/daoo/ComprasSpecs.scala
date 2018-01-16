package daoo

import com.unitec.dao.CompraDao
import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.matcher.BeEqualTo
import org.specs2.runner.JUnitRunner
import br.com.devQueijo.model.Compra.Compra

@RunWith(classOf[JUnitRunner])
class ComprasSpecs extends Specification {

  "compras dao " should {
    "Busca todos" in {
      true
    }

  }
  "compras teste" should {

    "Primeira Compra" in {
      1 must_== 1
    }

    "Segunda compra" in {
      4 must_== 5
    }.pendingUntilFixed("ISSUE-123")
  }

}