package com.nekopiano.scala.cats.underscore.ch01

import utils.CodeUtility._

object Chapter1 extends App {

  // show
  // eq
  // type safe with invariance

  {
    // 1.4.3 Importing Interface Syntax
    import cats.instances.all._
    import cats.syntax.all._

    val shownInt = 123.show
    // shownInt: String = 123
    println(inspect(shownInt))

    val shownString = "abc".show
    // shownString: String = abc
    println(inspect(shownString))

  }

  {
    // 1.4.5 Defining Custom Instances
    import java.util.Date
    import cats.Show
    import cats.syntax.show._

    implicit val dateShowa: Show[Date] =
      new Show[Date] {
        def show(date: Date): String =
          s"${date.getTime}ms since the epoch."
      }

    val date = new Date
    val shownDate = date.show
    println(inspect(shownDate))

  }

  {
    // 1.5.5 Exercise: Equality, Liberty, and Felinity
    final case class Cat(name: String, age: Int, color: String)

    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 32, "orange and black")
    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]

    import cats.Eq
    import cats.syntax.eq._ // for ===
    import cats.instances.int._ // for Eq
    import cats.instances.string._ // for Eq

    implicit val catEqual: Eq[Cat] =
      Eq.instance[Cat] { (cat1, cat2) =>
        (cat1.name === cat2.name) &&
          (cat1.age === cat2.age) &&
          (cat1.color === cat2.color)
      }

    val comp1 = cat1 === cat2
    // res17: Boolean = false
    println(inspect(comp1))
    val comp2 = cat1 =!= cat2
    // res18: Boolean = true
    println(inspect(comp2))


    import cats.instances.option._ // for Eq

    val compMaybe1 = optionCat1 === optionCat2
    // res19: Boolean = false
    println(inspect(compMaybe1))
    val compMaybe2 = optionCat1 =!= optionCat2
    // res20: Boolean = true
    println(inspect(compMaybe2))

  }
  {
    // 1.6.1 Variance
    trait Covariance[+A]
    // cast to a super type
    trait Contravariance[-A]
    // cast to a sub type
    trait Invariance[A]
    // impossible to cast

    // Cats generally prefers to use invariant type classes.
  }




}
