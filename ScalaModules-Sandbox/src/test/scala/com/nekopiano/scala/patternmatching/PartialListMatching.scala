package com.nekopiano.scala.patternmatching

/**
  * Created on 24/07/2017.
  */
object PartialListMatching extends App {

  val x = List(0xFC, 0x03, 0x01, 0x02, 0x03, 0x0A, 0x0D)
  x match {
    case Message(payload) => println("payload="+payload)
    case _ => println("No match")
  }

  //https://stackoverflow.com/questions/7915559/pattern-match-end-middle-of-list-in-scala
  object :!: {
    type LengthPayload = (Int, List[Int]) // (len, payload)
    // returns ((len, payload), unparsed)
    def unapply(z: List[Int]): Option[(LengthPayload, List[Int])] = {
      if (z == Nil) None
      else {
        val len = z.head
        // use ListBuffer to traverse the list only once
        val buf = collection.mutable.ListBuffer[Int]()
        def take(l: Int, list: List[Int]): Option[(LengthPayload, List[Int])] = {
          list match {
            case Nil if l > 0 => None
            case _ if l == 0 => Some((len, buf.toList), list)
            case _ => buf += list.head; take(l - 1, list.tail)
          }
        }
        take(len, z.tail)
      }
    }
  }
  object Message {
    def unapply(z: List[Int]): Option[List[Int]] = z match {
      case 0xFC :: (len, payload) :!: 0x0A :: 0x0D :: Nil =>
        println("len="+len)
        Some(payload)
      case _ => None
    }
  }

}
