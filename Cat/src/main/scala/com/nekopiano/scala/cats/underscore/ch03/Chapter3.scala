package com.nekopiano.scala.cats.underscore.ch03

import utils.CodeUtility._

object Chapter3 extends App {

  {
    // 3.2 More Examples of Functors

    import cats.instances.function._ // for Functor
    import cats.syntax.functor._ // for map

    val func1: Int => Double =
      (x: Int) => x.toDouble

    val func2: Double => Double =
      (y: Double) => y * 2

    val computed1 = (func1 map func2) (1) // composition using map
    // res7: Double = 2.0
    println(inspect(computed1))
    // These lines depend on
    // scalacOptions += "-Ypartial-unification"
    //    func1.map(func2)
    //    // <console>: error: value map is not a member of Int => Double
    //    //        func1.map(func2)
    //                    ^

    val computed2 = (func1 andThen func2) (1) // composition using andThen
    // res8: Double = 2.0
    println(inspect(computed2))

    val computed3 = func2(func1(1)) // composition written out by hand
    // res9: Double = 2.0
    println(inspect(computed3))

    val func =
      ((x: Int) => x.toDouble).
        map(x => x + 1).
        map(x => x * 2).
        map(x => x + "!")

    val computed4 = func(123)
    // res10: String = 248.0!
    println(inspect(computed4))

  }
  {
    // 3.4 Aside: Higher Kinds and Type Constructors

    import scala.language.higherKinds
    // or
    // scalacOptions += "-language:higherKinds"
    // for [_]

    import cats.Functor
    import cats.instances.function._ // for Functor
    import cats.syntax.functor._ // for map

//    // Declare F using underscores:
//    def myMethod[F[_]] = {
//
//      // Reference F without underscores:
//      val functor = Functor.apply[F]
////      Error:(65, 34) could not find implicit value for parameter instance: cats.Functor[F]
////      val functor = Functor.apply[F]
//      // TODO evade
//
//      // ...
//    }

  }
  {
    // 3.5.1 The Functor Type Class

    import scala.language.higherKinds
    import cats.Functor
    import cats.instances.list._   // for Functor
    import cats.instances.option._ // for Functor

    val list1 = List(1, 2, 3)
    // list1: List[Int] = List(1, 2, 3)
    println(inspect(list1))

    val list2 = Functor[List].map(list1)(_ * 2)
    // list2: List[Int] = List(2, 4, 6)
    println(inspect(list2))

    val option1 = Option(123)
    // option1: Option[Int] = Some(123)
    println(inspect(option1))

    val option2 = Functor[Option].map(option1)(_.toString)
    // option2: Option[String] = Some(123)
    println(inspect(option2))



    val func = (x: Int) => x + 1
    // func: Int => Int = <function1>

    val liftedFunc = Functor[Option].lift(func)
    // liftedFunc: Option[Int] => Option[Int] = cats.Functor$$Lambda$11698/1371437204@27fbe7ae

    val liftedRes = liftedFunc(Option(1))
    // res0: Option[Int] = Some(2)
    println(inspect(liftedRes))

  }
  {
    // 3.5.2 Functor Syntax

    import cats.instances.function._ // for Functor
    import cats.syntax.functor._     // for map

    val func1 = (a: Int) => a + 1
    val func2 = (a: Int) => a * 2
    val func3 = (a: Int) => a + "!"
    //val func4 = func1.map(func2).map(func3)
    val func4 = func1 map func2 map func3

    val res1 = func4(123)
    // res1: String = 248!
    println(inspect(res1))



    import cats.Functor
    import scala.language.higherKinds

    def doMath[F[_]](start: F[Int])
                    (implicit functor: Functor[F]): F[Int] =
      start.map(n => n + 1 * 2)

    import cats.instances.option._ // for Functor
    import cats.instances.list._   // for Functor

    val res3 = doMath(Option(20))
    // res3: Option[Int] = Some(22)
    println(inspect(res3))

    val res4 = doMath(List(1, 2, 3))
    // res4: List[Int] = List(3, 4, 5)
    println(inspect(res4))


  }
  {
    // 3.5.3 Instances for Custom Types
    import cats.Functor
    import scala.concurrent.{Future, ExecutionContext}

    implicit def futureFunctor
    (implicit ec: ExecutionContext): Functor[Future] =
      new Functor[Future] {
        def map[A, B](value: Future[A])(func: A => B): Future[B] =
          value.map(func)
      }

    // the following is equivalent to `implicit val ec = ExecutionContext.global`
    import ExecutionContext.Implicits.global

    // We write this:
    val a = Functor[Future]

    // The compiler expands to this first:
    val b = Functor[Future](futureFunctor)

    // And then to this:
    val c = Functor[Future](futureFunctor(global))

  }
  {
    // 3.5.4 Exercise: Branching out with Functors
    sealed trait Tree[+A]
    final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]
    final case class Leaf[A](value: A) extends Tree[A]

    import cats.Functor
    //import scala.language.higherKinds

    implicit val treeFunctor: Functor[Tree] =
      new Functor[Tree] {
        def map[A, B](tree: Tree[A])(func: A => B): Tree[B] =
          tree match {
            case Branch(left, right) =>
              Branch(map(left)(func), map(right)(func))
            case Leaf(value) =>
              Leaf(func(value))
          }
      }

    object Tree {
      def branch[A](left: Tree[A], right: Tree[A]): Tree[A] = Branch(left, right)
      def leaf[A](value: A): Tree[A] = Leaf(value)
    }

    import cats.syntax.functor._     // for map

    val res10 = Tree.leaf(100).map(_ * 2)
    // res10: wrapper.Tree[Int] = Leaf(200)
    println(inspect(res10))

    val res11 = Tree.branch(Tree.leaf(10), Tree.leaf(20)).map(_ * 2)
    // res11: wrapper.Tree[Int] = Branch(Leaf(20),Leaf(40))
    println(inspect(res11))

  }
  {
    // 3.7 Contravariant and Invariant in Cats

    // https://docs.scala-lang.org/tour/variances.html
//    class Foo[+A] // A covariant class
//    class Bar[-A] // A contravariant class
//    class Baz[A]  // An invariant class

    import cats.Show
    import cats.instances.string._
    import cats.syntax.contravariant._ // for contramap

    val showString = Show[String]
    val res3 = showString.contramap[Symbol](_.name).show('dave)
    // res3: String = dave
    println(inspect(res3))

  }
  {
    // 3.7.2 Invariant in Cats
    import cats.Monoid
    import cats.instances.string._ // for Monoid
    import cats.syntax.invariant._ // for imap
    import cats.syntax.semigroup._ // for |+|

    implicit val symbolMonoid: Monoid[Symbol] =
      Monoid[String].imap(Symbol.apply)(_.name)

    val res = Monoid[Symbol].empty
    // res5: Symbol = '
    println(inspect(res))

    val res2 = 'a |+| 'few |+| 'words
    // res6: Symbol = 'afewwords
    println(inspect(res2))
  }

}
