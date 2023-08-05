package com.nekopiano.scala.vanilla

import scala.concurrent.Future
import scala.util.{Failure, Success}

import utils.CodeUtility._

object VanillaFutureCompositionApp extends App {

  implicit val ec = scala.concurrent.ExecutionContext.global

  val futA = Future {1}
  val futB = Future[Int] {throw new RuntimeException("an exception.")}
  val futC = Future {2}

  val res = for {
    a <- futA
    b <- futB
  } yield a

  println(res)
  // Future(Failure(java.lang.RuntimeException: an exception.))

  val res2 = for {
    a <- futA
    b <- futC
  } yield a

  println(res2)
  res2 onComplete {
    case Success(value) => println(inspect(value))
    case Failure(exception) => println(inspect(exception))
  }


}
