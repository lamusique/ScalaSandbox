/**
 *
 */
package com.nekopiano.scala.sandbox

import com.twitter.util.Eval

/**
 * @author La Musique
 *
 */
object NonEmptyTest {

  def main(args: Array[String]): Unit = {

    println("aa".nonEmpty)
    println("".nonEmpty)
    println(List("a").nonEmpty)
    println(List("").nonEmpty)
    println(List().nonEmpty)
    println(List.empty.nonEmpty)
    //true
    //false
    //true
    //true
    //false
    //false

    // val i = new scala.tools.nsc.Interpreter(new Settings(null))
    val sum = Eval[Int]("1 + 1")
    println("sum=" + sum)

    val seq = Seq(raw""""aa".nonEmpty""",
      raw""""".nonEmpty""",
      raw"""List("a").nonEmpty""",
      raw"""List("").nonEmpty""",
      raw"""List().nonEmpty""",
      raw"""List.empty.nonEmpty""")

    seq foreach (code => {
      val r = Eval[Boolean](code)
      println(code + "=" + r)
    })

    //"aa".nonEmpty=true
    //"".nonEmpty=false
    //List("a").nonEmpty=true
    //List("").nonEmpty=true
    //List().nonEmpty=false
    //List.empty.nonEmpty=false

  }

}
