package example

object HelloB extends GreetingB with App {
  println(greeting)
}

trait GreetingB {
  lazy val greeting: String = "hello B " + A()
}

case class B()
