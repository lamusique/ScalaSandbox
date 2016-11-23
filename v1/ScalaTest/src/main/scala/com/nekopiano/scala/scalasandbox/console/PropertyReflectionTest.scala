/**
 *
 */
package com.nekopiano.scala.scalasandbox.console

/**
 * @author La Musique
 *
 */
object PropertyReflectionTest {

  // http://stackoverflow.com/questions/12797300/in-scala-how-to-turn-objects-values-into-mapstring-string

  def main(args: Array[String]): Unit = {

    val test = Test(23423, "sdlkfjlsdk")
    val map = domain2map(test)
    println(map)
  }

  case class Test(id: Long, name: String)

  def domain2map(domain: Object) = {
    import reflect.runtime.universe._
    import reflect.runtime.currentMirror

    val r = currentMirror.reflect(domain)
    r.symbol.typeSignature.members.toStream
      .collect { case s: TermSymbol if !s.isMethod => r.reflectField(s) }
      .map(r => r.symbol.name.toString.trim -> r.get.toString)
      .toMap
  }
}
