package utils

import scala.annotation.tailrec

object ListUtility {

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

  def packLabeled[A, B](list: List[(A, B)]): List[(B, List[(A)])] = {

    @tailrec
    def packInner(l: List[(A, B)], cur: List[(A, B)], acc: List[(B, List[(A)])]): List[(B, List[(A)])] = l match {
      case e :: tail if cur.isEmpty => packInner(tail, List(e), acc)
      case e :: tail if e._2 == cur.head._2 => packInner(tail, e :: cur, acc)
      case e :: tail if e._2 != cur.head._2 => packInner(tail, List(e), (cur.head._2, cur.map(_._1)) :: acc)
      case Nil => (cur.head._2, cur.map(_._1)) :: acc
    }

    packInner(list, List.empty, List.empty).reverse
  }

}

