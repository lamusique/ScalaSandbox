/**
 *
 */
package com.nekopiano.scala.scalasandbox.types

/**
 * @author La Musique
 *
 */
class InheritanceTest extends App {

}

class AA(val string: String)
//class BB(val string: String) extends A
class BB(override val string: String) extends AA(string)


class A(a:Int) {
}

class B extends A(123) {
}

class C(n:Int) extends A(n) {
}

