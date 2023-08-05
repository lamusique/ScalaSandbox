/**
 *
 */
package com.nekopiano.scala.scalasandbox.console

import pl.project13.scala.rainbow.Rainbow._
import org.fusesource.jansi.AnsiConsole

/**
 * @author La Musique
 *
 */
object ColoredConsoleTest {

  def main(args: Array[String]): Unit = {
    // coloring doesn't work on Windows Eclipse.
    // you can use an Eclipse Plugin, ANSI Escape in Console.

    // rainbow
    val coloredText = "Magenta Color".magenta
    println(coloredText)
    println { "black".black + "red".red + "green".green + "yellow".yellow + "blue".blue + "magenta".magenta + "cyan".cyan + "white".white }
    println { "onBlack".white + "onRed".white.onRed + "onGreen".white.onGreen + "onYellow".white.onYellow + "onBlue".white.onBlue + "onMagenta".white.onMagenta + "onCyan".white.onCyan + "onWhite".white.onWhite }

    
    // jansi
    AnsiConsole.systemInstall
    AnsiConsole.out.println(coloredText)
    AnsiConsole.out.println("black".black + "red".red + "green".green + "yellow".yellow + "blue".blue + "magenta".magenta + "cyan".cyan + "white".white)
    AnsiConsole.out.println("onBlack".white + "onRed".white.onRed + "onGreen".white.onGreen + "onYellow".white.onYellow + "onBlue".white.onBlue + "onMagenta".white.onMagenta + "onCyan".white.onCyan + "onWhite".white.onWhite)
    
    
    // scala.Console itself
    val answer = Console.readLine(Console.BLACK_B + "What is " + Console.BLINK + "your" + Console.RED + " name?")
    println(answer)
    // Eclipse shows:
    // [40mWhat is [5myour[31m name?
    val readInt = Console.readInt
    println(readInt)

    println { "Red warning".red }
  }

}
