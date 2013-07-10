/**
 *
 */
package com.nekopiano.scala.scalasandbox

import pl.project13.scala.rainbow.Rainbow._

/**
 * @author La Musique
 *
 */
object PromptTest {

  def main(args: Array[String]): Unit = {
    // coloring doesn't work on Windows Eclipse.
    val answer = Console.readLine(Console.BLACK_B + "What is " + Console.BLINK + "your" + Console.RED + " name?")
    println(answer)
    val readInt = Console.readInt
    println(readInt)

    println { "Red warning".red }
  }

}
