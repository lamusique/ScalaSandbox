package quizzes

import utest._

import scala.annotation.tailrec

object Scala99QuizzesTest extends TestSuite {

  import utils.LoggingUtility._

  val tests = Tests {

    // Scala 99 Quizzes Q09
    'p09 - {

      // Make it tail recursive

      def pack[T](list: List[T]): List[List[T]] = {

        @tailrec
        def packInner(l: List[T], cur: List[T], acc: List[List[T]]): List[List[T]] = l match {
          case e :: tail if cur.isEmpty => packInner(tail, List(e), acc)
          case e :: tail if e == cur.head => packInner(tail, e :: cur, acc)
          case e :: tail if e != cur.head => packInner(tail, List(e), cur :: acc)
          case Nil => cur :: acc
        }

        packInner(list, List(), List.empty)
      }

      val result = pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
      println(inspect(result))
    }

    'p09b - {

      // detect by B

      def pack[A, B](list: List[(A, B)]): List[List[(A, B)]] = {

        @tailrec
        def packInner(l: List[(A, B)], cur: List[(A, B)], acc: List[List[(A, B)]]): List[List[(A, B)]] = l match {
          case e :: tail if cur.isEmpty => packInner(tail, List(e), acc)
          case e :: tail if e._2 == cur.head._2 => packInner(tail, e :: cur, acc)
          case e :: tail if e._2 != cur.head._2 => packInner(tail, List(e), cur :: acc)
          case Nil => cur :: acc
        }

        packInner(list, List.empty, List.empty).reverse
      }

      val result = pack(List(('a, false), ('a, true), ('a, true), ('a, false), ('b, false), ('c, true), ('c, false), ('a, true), ('a, true), ('d, false), ('e, true), ('e, true), ('e, false), ('e, true)))
      println(inspect(result))
    }

  }

}
