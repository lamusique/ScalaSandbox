/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object ShapelessTest {

  def main(args: Array[String]): Unit = {
    var hm = new HMap
    hm.set("hoge", 1)
    hm.set("fuga", "moge")
    hm.get[Int]("hoge")
    hm.get[String]("fuga")
    hm.get[String]("hoge")

    println(hm)
  }
  class HMap {
    import scala.reflect.runtime.universe.{ typeOf, TypeTag, Type }
    private var m = Map[String, (Type, _)]()
    def set[T: TypeTag](k: String, v: T): Unit = { m += k -> (typeOf[T] -> v) }
    def get[T: TypeTag](k: String): Option[T] = m.get(k) flatMap {
      case (t, v) if t <:< typeOf[T] => Some(v.asInstanceOf[T])
      case _ => None
    }
  }
}
