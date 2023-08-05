package example

object HelloA extends GreetingA with App {
  println(greeting)
}

trait GreetingA {
  lazy val greeting: String = "hello A"
}

case class A()
