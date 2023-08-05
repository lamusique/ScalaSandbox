package utils

import scala.annotation.tailrec

object CharacterUtility {

  import LoggingUtility._

  def normalise(text: String) = {
    full2half(text)
  }


  def full2half(mixedText: String) = {

    val numberMapping = Map("一" -> 1)

    mixedText.map {
      // symbols A
      case c:Char if c >= 0xFF01 && c <= 0xFF0F => (c - 0xFEE0).toChar
      // numbers
      case c:Char if c >= 0xFF10 && c <= 0xFF19 => (c - 0xFEE0).toChar
      // small alphabets
      case c:Char if c >= 0xFF21 && c <= 0xFF3A => (c - 0xFEE0).toChar
      // capital alphabets
      case c:Char if c >= 0xFF41 && c <= 0xFF5A => (c - 0xFEE0).toChar
      // symbols B
      case c:Char if c >= 0xFF5B && c <= 0xFF5E => (c - 0xFEE0).toChar
      case c:Char => c
    }
  }


  def detectMatched(inputTextSearchedBy: String, textToSearch: String): List[(Char, Boolean)] = {

    val input = inputTextSearchedBy.toList
    val size = input.size
    val evaledInput = input.map((_, true))

    @tailrec
    def detectMatchedInternal(list: List[Char], acc: List[(Char, Boolean)]): List[(Char, Boolean)] = {
      list match {
        case (x :: xs) => {

          val taken = list.take(size)
          val next = if (size > 0 && taken == input) {
            val nextList = list.drop(size)
            val appendingList = evaledInput.reverse
            (nextList, appendingList ::: acc)
          } else {
            val nextList = list.tail
            val appendingHead = list.head
            (nextList, (appendingHead, false) :: acc)
          }

          //println(inspect(next))
          detectMatchedInternal(next._1, next._2)
        }
        case Nil => acc.reverse
      }
    }
    detectMatchedInternal(textToSearch.toList, List.empty)
  }



  import scala.util.parsing.combinator._

  object PrsKsj {
    def apply(s:String) = {
      val p = new PrsKsj
      p.parseAll(p.root,s)
    }
  }

  class PrsKsj extends JavaTokenParsers {
    val k2d = Seq("壱","弐","参","四","五","六","七","八","九") zip (1 to 9) toMap
    def root = opt(千の位) ~ opt(百の位) ~ opt(十の位) ~ opt(一の位) ^^ {
      case s ~ h ~ j ~ i => Seq(s,h,j,i).foldLeft(0){(l,r) => l + r.getOrElse(0)}
    }
    def ksj = "壱" | "弐" | "参" | "四" | "五" | "六" | "七" | "八" | "九"
    def 一の位 = ksj ^^ k2d
    def 十の位 = unitPrs("拾",10)
    def 百の位 = unitPrs("百",100)
    def 千の位 = unitPrs("千",1000)
    def unitPrs(u:String,mul:Int) = opt(ksj) <~ u ^^ {
      case Some(d) => k2d(d) * mul
      case None => mul
    }
  }


}
