/**
 *
 */
package com.nekopiano.scala.sandbox.matching

/**
 * @author La Musique
 *
 */
object WhichMachesFirst extends App {

  val tuple = (1, 2, 3)

  val result1 = tuple match {
    case (_, 2, _) => { 2 }
    case (_, _, 3) => { 3 }
    case (_, _, _) => { 0 }
  }

  println("result1=" + result1)
  // result=2
  
  val result2 = tuple match {
    case (_, 3, _) => { 3 }
  }
  //Exception in thread "main" scala.MatchError: (1,2,3) (of class scala.Tuple3)

  println("result2=" + result2)
  
}

