package utils

import utest._

object ListUtilityTest extends TestSuite {

  import utils.LoggingUtility._

  val tests = Tests{
    'match - {

      val arg = List(('a, false), ('b, true), ('c, true), ('d, false), ('e, false), ('f, true), ('g, false), ('h, true), ('i, true), ('j, false), ('k, true), ('l, true), ('m, false), ('n, true))
      val result = ListUtility.packLabeled(arg)
      println(inspect(result))
    }
  }
}
