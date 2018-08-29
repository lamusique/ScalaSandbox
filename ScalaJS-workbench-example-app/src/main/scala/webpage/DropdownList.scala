package webpage

import org.scalajs.dom
import dom.html
import dom.document

import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

import scala.annotation.tailrec
import scala.util.{Failure, Success}


//@JSExport
//object HelloWorld extends {
  //@JSExport
  //def main(target: html.Div) = {

object DropdownList {

  import utils.CharacterUtility._
  import utils.LoggingUtility._
  import utils.ListUtility._


  def main(args: Array[String]): Unit = {

    //example.ScalaJSExample().sub(document.getElementsByClassName('scalajsDiv')[0])
    //document.evaluate()
    val targetDiv = document.getElementsByClassName("scalajsDiv").item(0)
    //println(targetDiv)

    val (animalA, animalB) = ("fox", "dog")
    targetDiv.appendChild(
      div(
        h1("Hello World!"),
        p(
          "The quick brown ", b(animalA),
          " jumps over the lazy ",
          i(animalB), "."
        )
      ).render
    )



    val box = input(
      `type`:="text",
      placeholder:="Type here!"
    ).render


    val listings = Seq(
      "Apple", "Apricot", "Banana", "Cherry",
      "Mango", "Mangosteen", "Mandarin",
      "Grape", "Grapefruit", "Guava", "壱弐参会社壱弐参", "名前会社名前",
      "一二三太郎会社", "１２３Ａｐｐｌｅ会社"
    )

//    def renderListings = ul(
//      for {
//        fruit <- listings
//        if fruit.toLowerCase.startsWith(
//          box.value.toLowerCase
//        )
//      } yield li(fruit)
//    ).render



    def renderListings = ul({

//      val lcInput = box.value.toLowerCase
//      val normalisedInput = full2half.transliterate(lcInput)
      //val peeledInput = full2half.transliterate(box.value).toLowerCase
      val peeledInput = normalise(box.value).toLowerCase

      val mixFruits = listings.map(freshFruit => {
        val peeledFruit = normalise(freshFruit).toLowerCase
        detectMatched(peeledInput, peeledFruit)
      })

      val fruitBasket = mixFruits.map(packLabeled(_))

//      fruitBasket.map(mixFruits => {
//
//        li(
//          mixFruits.map {
//              case (true, cutFruit: List[Char]) => span(
//                backgroundColor := "lightpink",
//                cutFruit.mkString
//              )
//              case (false, cutFruit: List[Char]) => span(
//                backgroundColor := "white",
//                cutFruit.mkString
//              )
//            }
//          )
//        })




//      listings.filter(normalise(_).toLowerCase.contains(peeledInput)).map(freshFruit => {
//        //val lcFruit = fruit.toLowerCase
//        val peeledFruit = normalise(freshFruit).toLowerCase
//        val sandwichFruit = "^^" + peeledFruit + "$$"
//
//        val notMatched = {
//          //val splitted = sandwichFruit.split(lcInput).map(notMatched => (notMatched, false)).toSeq
//          val splitted = sandwichFruit.split(peeledInput).toSeq
//
//          val origHead = splitted.head.drop(2)
//          val withHead = if (origHead.isEmpty) splitted.tail.init else origHead +: splitted.tail.init
//
//          val origTail = splitted.last.dropRight(2)
//          val withTail = if (origTail.isEmpty) withHead else withHead :+ origTail
//
//          //val origSplitted = origHead +: splitted.tail.init :+ origTail
//          //origSplitted.map((_, false))
//
//          withTail.map((_, false))
//        }
//
//        //println("notMatched: " + notMatched)
//        println(inspect(notMatched))
//        //val matchedParts = (1 to notMatched.size).map(i => (peeledInput, true))
////        val matchedParts = (1 to notMatched.size).toList match {
////          case numbers: List[Int] => numbers.map(i => (peeledInput, true))
////          case Nil => List((peeledInput, true))
////        }
//        val matchedParts = (0 until notMatched.size).map(i => (peeledInput, true))
//
//        //println("matchedParts: " + matchedParts)
//        println(inspect(matchedParts))
//        val zipped = if (peeledFruit.indexOf(peeledInput) == 0) matchedParts.zipAll(notMatched, ("", false), ("", false)) else notMatched.zipAll(matchedParts, ("", false), ("", false))
//        val capsulisedFruit = zipped.flatMap {case (a, b) => Seq(a, b)}
//        println(freshFruit + ": " + capsulisedFruit)
//        val charSizes = capsulisedFruit.map(_._1.size).scan(0)((acc, n) => acc + n)
//        val originalFruit = charSizes.sliding(2).toSeq.map{group => {
//          //println("fruit=" + fruit + ", g1=" + group(0) + ", g2=" + group(1))
//          freshFruit.substring(group(0), group(1))
//        }}
//        val mixFruit = capsulisedFruit.zip(originalFruit)
//
//        li(
//          mixFruit.map {
////             case ((_: String, true), cutFruit: String) => span(
////               backgroundColor := "skyblue",
////               cutFruit
////             )
////             case ((_: String, false), cutFruit: String) => span(cutFruit)
//            case ((_: String, true), cutFruit: String) => span(
//              backgroundColor := "lightpink",
//              cutFruit
//            )
//            case ((_: String, false), cutFruit: String) => span(
//              backgroundColor := "white",
//              cutFruit
//            )
//         }
//        )
//
//      })


      //li("a", 1)

//      for {
//        fruit <- listings
//        lowerFruit <- fruit.toLowerCase()
//        startIndex <- lowerFruit.indexOf(box.value.toLowerCase)
//        if fruit.toLowerCase.contains(inp)
//      } yield {
//        fruit.toLowerCase.indexOf(box.value.toLowerCase)
//
//        val (first, last) = fruit.splitAt(
//          box.value.length
//        )
//        li(
//          span(
//            backgroundColor := "yellow",
//            first
//          ),
//          last
//        )
//      }


      }
    ).render



    val output = div(renderListings).render


//    targetDiv.appendChild(
//      div(
//        h1("Capital Box!"),
//        p(
//          "Type here and " +
//            "have it capitalized!"
//        ),
//        div(box),
//        div(output)
//      ).render
//    )

//    window.onclick = (e: dom.Event) => {
//      println("win onclick e.target=" + e.target)
//      println("win onclick e.currentTarget=" + e.currentTarget)
//    }

    box.onfocus = (e: dom.Event) => {
      println("focus e.target=" + e.target)
      println("focus e.currentTarget=" + e.currentTarget)

//      output.style(css("background-color") := "red")
//      output.style(backgroundColor:="blue")

      output.style.display = "inline"

    }
    box.onblur = (e: dom.Event) => {
      println("blur e.target=" + e.target)
      println("blur e.currentTarget=" + e.currentTarget)

      output.style.display = "none"
    }

//    box.onfocusin = (e: dom.Event) => {
//      println("in")
//    }
//    box.onfocusout = (e: dom.Event) => {
//      println("out")
//    }


    box.onkeyup = (e: dom.Event) => {
      output.innerHTML = ""
      output.appendChild(renderListings)
    }

    targetDiv.appendChild(
      div(
        h1("Search Box!"),
        p(
          "Type here to filter " +
            "the list of things below!"
        ),
        div(box),
        output
      ).render
    )


  }

}
