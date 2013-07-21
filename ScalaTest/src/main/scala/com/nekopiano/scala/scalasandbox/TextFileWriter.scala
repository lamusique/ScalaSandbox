/**
 *
 */
package com.nekopiano.scala.scalasandbox

/**
 * @author La Musique
 *
 */
object TextFileWriter {

  def main(args: Array[String]): Unit = {
    // http://stackoverflow.com/questions/6879427/scala-write-string-to-file-in-one-statement

    // http://stackoverflow.com/questions/9886123/executing-shell-commands-from-scala-repl

    import sys.process._
    //    "echo hello world" #> new java.io.File("example.txt") !
    //    "dir /b" #> new java.io.File("example.txt")

    //    val process = Process("cmd /C dir") !
    "cmd /C echo hello world" #> new java.io.File("example.txt") !

  }

}
