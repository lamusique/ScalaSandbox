package utils

import utest._

object CharacterUtilityTest extends TestSuite {

  import utils.LoggingUtility._
  import utils.CharacterUtility._

  val tests = Tests{
    'test1 - {
      //throw new Exception("test1")
    }
    'test2 - {
      1
    }
    'test3 - {
      val a = List[Byte](1, 2)
      //a(10)
    }
    'ChineseNumbers - {
          Seq(("一",1),("壱",1),("あ一百ご",100),("百",100),("千",1000),("千壱",1001),("千弐拾",1020),
            ("百五",105),("六千参百拾壱",6311),("unmatch data",33)
          ) foreach {t => PrsKsj(t._1) match {
            case x if(x.successful) => print("test:" + t);assert(x.get == t._2);println(":OK")
            case x => println(t,x)
          }}
    }
    'match - {
      val result = CharacterUtility.detectMatched("Ab", "$dAbcAbdab")
      println(inspect(result))
    }
  }
}
