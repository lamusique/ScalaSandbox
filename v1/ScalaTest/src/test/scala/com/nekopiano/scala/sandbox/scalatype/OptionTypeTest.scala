/**
 *
 */
package com.nekopiano.scala.sandbox.scalatype

/**
 * @author La Musique
 *
 */
object OptionTypeTest {

  def main(args: Array[String]): Unit = {
    treatOption("testa")
    treatOption(null)

    treatOptionAsParam(Option("testb"))
    treatOptionAsParam(None)
    val a: Option[String] = null
    treatOptionAsParam(a)
    treatOptionAsParam(null)
  }

//  implicit def null2None(n: Null) = None
  implicit def string2Option(str: String) = Option(str)
  
  def treatOption(str: String) = {
    Option(str) foreach println _
  }
  def treatOptionAsParam(str: Option[String]) = {
    str foreach println _
  }

}
