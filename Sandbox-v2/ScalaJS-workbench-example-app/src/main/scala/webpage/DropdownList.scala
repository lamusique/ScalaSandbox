package webpage

import org.scalajs.dom
import dom.html
import dom.document

import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

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

    val targetDiv = document.getElementsByClassName("scalajsDiv").item(0)

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


    def renderListings = ul({

      val peeledInput = normalise(box.value).toLowerCase

      val mixFruits = listings.map(freshFruit => {
        val peeledFruit = normalise(freshFruit).toLowerCase
        val matched = detectMatched(peeledInput, peeledFruit)
        // Change string used for matching to its original.
        matched.zip(freshFruit).map(fruitPunch => (fruitPunch._2, fruitPunch._1._2))
      })
      println(inspect(mixFruits))

      val fruitBasket = mixFruits.map(packLabeled(_))
      println(inspect(fruitBasket))

      val ripeFruits = fruitBasket.filter(pickedFruit => pickedFruit.exists(_._1))

      val fruitsOnDisplay = if (peeledInput.isEmpty) fruitBasket else ripeFruits

      fruitsOnDisplay.map(mixFruits => {

        li(
          mixFruits.map {
              case (true, cutFruit: List[Char]) => span(
                backgroundColor := "lightpink",
                cutFruit.mkString
              )
              case (false, cutFruit: List[Char]) => span(
                backgroundColor := "white",
                cutFruit.mkString
              )
            }
          )
        })


      }
    ).render



    val output = div(renderListings).render


    box.onfocus = (e: dom.Event) => {
      println("focus e.target=" + e.target)
      println("focus e.currentTarget=" + e.currentTarget)

      output.style.display = "inline"

    }
    box.onblur = (e: dom.Event) => {
      println("blur e.target=" + e.target)
      println("blur e.currentTarget=" + e.currentTarget)

      output.style.display = "none"
    }


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
