/**
 *
 */
package com.nekopiano.scala.scalasandbox.console

/**
 * @author La Musique
 *
 */
object ProgressPrintDemo {

  def main(args: Array[String]): Unit = {

    println("START")
    println("PROGRESS:")

    for (i <- 0 until 10) {
      print('|')
      Thread.sleep(1000)
    }
    println
    println("FINISHED")

  }

}
