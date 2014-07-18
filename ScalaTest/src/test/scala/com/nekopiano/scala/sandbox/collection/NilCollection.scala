/**
 *
 */
package com.nekopiano.scala.sandbox.collection

/**
 * @author La Musique
 *
 */
object NilCollection extends App {

  var seq = Seq.empty[Option[String]]
  seq = Seq(None, None, None)

  println("seq=" + seq)
  println("(seq == Nil)=" + (seq == Nil))
  // false

  println("""seq.mkString("/")=""" + seq.mkString("/"))

  val firstDefined = seq.exists(_.isDefined)
  println("firstDefined=" + firstDefined)
  // false
  seq = Seq(None, Option(""), None)
  val firstDefined2 = seq.exists(_.isDefined)
  println("firstDefined2=" + firstDefined2)
  // true

}

