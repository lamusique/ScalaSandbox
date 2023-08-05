/**
 *
 */
package com.nekopiano.scala.sandbox.scalatype

// com.nekopiano.scala.sandbox.type
// type is reserved so it's not allowed...

/**
 * @author La Musique
 *
 */
object HashTableTypeTest {

  def main(args: Array[String]): Unit = {

    // Map
    val map = Map("1" -> "a") ++ Map("2" -> "b")
    println("map=" + map)
    // Tuple
    //    val tuple = ("1" -> "a") + ("2" -> "b")
    val tuple = "1" -> "a" + "2" -> "b"
    println("tuple=" + tuple)
    // tuple=((1,a)2,b)
    // so this line doesn't work as hash table...

    val seq = Seq((1, "type-a", "value-aa"), (1, "type-a", "value-ab"), (1, "type-b", "value-ba"), (2, "type-c", "value-ca"))
    val result = seq flatMap (e => {
      Map(e._1 -> e._2)
    })
    println("result=" + result)
    println("result.toMap=" + result.toMap)
    // result.toMap=Map(1 -> type-b, 2 -> type-c)
  }

}
