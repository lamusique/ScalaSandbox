package com.nekopiano.scala.cats.underscore.ch02

import utils.CodeUtility._

object Chapter2 extends App {

  {
    // 2.5.1 The Monoid Type Class

    // A semigroup is a monoid with an identity, which has associativity, i.e. combine() in Cats.

    import cats.Monoid
    import cats.instances.int._ // for Monoid
    import cats.instances.option._ // for Monoid

    val a = Option(22)
    // a: Option[Int] = Some(22)
    println(inspect(a))

    val b = Option(20)
    // b: Option[Int] = Some(20)
    println(inspect(b))

    val combined = Monoid[Option[Int]].combine(a, b)
    // This goes to OptionMonoid.
    // res6: Option[Int] = Some(42)
    println(inspect(combined))

    // the same result with a sign.
    import cats.syntax.semigroup._ // for |+|
    val combined2 = a |+| b
    println(inspect(combined2))

  }
  {
    // 2.7 Summary
    import cats.Monoid
    import cats.instances.int._ // for Monoid
    import cats.instances.string._ // for Monoid
    import cats.syntax.semigroup._ // for |+|
    import cats.instances.map._ // for Monoid

    val map1 = Map("a" -> 1, "b" -> 2)
    val map2 = Map("b" -> 3, "d" -> 4)

    val combined = map1 |+| map2
    // res3: Map[String,Int] = Map(b -> 5, d -> 4, a -> 1)
    println(inspect(combined))

    import cats.instances.tuple._ // for Monoid

    val tuple1 = ("hello", 123)
    val tuple2 = ("world", 321)

    val combined2 = tuple1 |+| tuple2
    // res6: (String, Int) = (helloworld,444)
    println(inspect(combined2))

    
    def addAll[A](values: List[A])
                 (implicit monoid: Monoid[A]): A =
      values.foldRight(monoid.empty)(_ |+| _)

    val added = addAll(List(1, 2, 3))
    // res7: Int = 6
    println(inspect(added))

    import cats.instances.option._ // for Monoid

    val added2 = addAll(List(None, Some(1), Some(2)))
    // res8: Option[Int] = Some(3)
    println(inspect(added2))
  }

}
