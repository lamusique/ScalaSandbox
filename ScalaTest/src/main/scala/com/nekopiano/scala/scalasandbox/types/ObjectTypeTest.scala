/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
object ObjectTypeTest extends App {

  val foo = new Foo
  receiveObject(foo)

  //receiveObject(Foo)
  //type mismatch; found : com.nekopiano.scala.scalasandbox.types.Foo.type required: com.nekopiano.scala.scalasandbox.types.Foo

  //receiveObject(Foo.instance)
  //type mismatch; found : com.nekopiano.scala.scalasandbox.types.Foo.type required: com.nekopiano.scala.scalasandbox.types.Foo

  def receiveObject(foo: Foo) = {

  }

}

class Foo {}
object Foo { def instance = this }

