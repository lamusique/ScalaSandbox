/**
 *
 */
package com.nekopiano.scala.sandbox

/**
 * @author La Musique
 *
 */
object DistinctTest {

  def main(args: Array[String]): Unit = {
    val redundantMap = Map(1 -> 11, 1 -> 12, 1 -> 13, 2 -> 21)
    println(redundantMap)
    // Map(1 -> 13, 2 -> 21)

    val redundantList = List(1 -> 11, 1 -> 12, 1 -> 13, 2 -> 21)
    println(redundantList.distinct)
    // List((1,11), (1,12), (1,13), (2,21))
  }

}
