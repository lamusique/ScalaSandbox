package utils

import utest._

object ListUtilityTest extends TestSuite {

  import utils.LoggingUtility._

  val tests = Tests{
    'match - {

      val arg = List(('a, false), ('a, true), ('a, true), ('a, false), ('b, false), ('c, true), ('c, false), ('a, true), ('a, true), ('d, false), ('e, true), ('e, true), ('e, false), ('e, true))
      val result = ListUtility.packLabeled(arg)
      println(inspect(result))
    }
  }
}
