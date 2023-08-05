/**
 *
 */
package com.nekopiano.scala.scalasandbox.collection

/**
 * @author La Musique
 *
 */
object AssociativeArrayTest extends App {
  util.Properties.setProp("scala.time", "AssociativeArrayTest")

  // Tuple
  val tuple = "a" -> 1
  println(tuple.getClass)
  //class scala.Tuple2

  val tuples = (("a" -> 1), ("b" -> 2), ("b" -> 3))
  println(tuples)
  //((a,1),(b,2),(b,3))
  // not compressed

  val map = Map(("a" -> 1), ("b" -> 2), ("b" -> 3))
  println(map)
  //Map(a -> 1, b -> 3)
  // compressed and overwritten
  
  val seq = Seq(("a" -> 1), ("b" -> 2), ("b" -> 3))
  println(seq)
  //List((a,1), (b,2), (b,3))
  println(seq.getClass)
  //class scala.collection.immutable.$colon$colon
  println(seq.toMap)
  //Map(a -> 1, b -> 3)
  // A Seq of Tuple2 can be converted to a Map.

}
