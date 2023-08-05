/**
 *
 */
package com.nekopiano.scala.sandbox

/**
 * @author La Musique
 *
 */
object Collection2StringTest {

  def main(args: Array[String]): Unit = {

    val empty = Set.empty
    val set = Set("a", "b")
    println(empty.mkString(","))
    // empty
    println(set.mkString(","))
    // a,b

  }

}
