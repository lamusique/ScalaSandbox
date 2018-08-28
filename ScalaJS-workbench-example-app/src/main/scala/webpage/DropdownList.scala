package webpage

import org.scalajs.dom
import dom.html
import dom.document
import scalajs.js.annotation.JSExport
import scalatags.JsDom.all._

//@JSExport
//object HelloWorld extends {
  //@JSExport
  //def main(target: html.Div) = {

object DropdownList {

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
      "Grape", "Grapefruit", "Guava"
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

      val lcInput = box.value.toLowerCase
      listings.filter(_.toLowerCase.contains(lcInput)).map(fruit => {
        val lcFruit = fruit.toLowerCase
        val sandwichFruit = "^^" + fruit.toLowerCase + "$$"


        val notMatched = {
          //val splitted = sandwichFruit.split(lcInput).map(notMatched => (notMatched, false)).toSeq
          val splitted = sandwichFruit.split(lcInput).toSeq

          val origHead = splitted.head.drop(2)
          val origTail = splitted.last.dropRight(2)
          val origSplitted = origHead +: splitted.tail.init :+ origTail
          origSplitted.map((_, false))
        }

        //println("notMatched: " + notMatched)
        val matchedParts = (2 to notMatched.size).map(i => (lcInput, true))
        //println("matchedParts: " + matchedParts)
        val zipped = if (lcFruit.indexOf(lcInput) == 0) matchedParts.zipAll(notMatched, ("", false), ("", false)) else notMatched.zipAll(matchedParts, ("", false), ("", false))
        val capsulisedFruit = zipped.flatMap {case (a, b) => Seq(a, b)}
        //println(fruit + ": " + capsulisedFruit)
        val charSizes = capsulisedFruit.map(_._1.size).scan(0)((acc, n) => acc + n)
        val originalFruit = charSizes.sliding(2).toSeq.map{group => {
          //println("fruit=" + fruit + ", g1=" + group(0) + ", g2=" + group(1))
          fruit.substring(group(0), group(1))
        }}
        val mixFruit = capsulisedFruit.zip(originalFruit)

        li(
          mixFruit.map {
             case ((_: String, true), cutFruit: String) => span(
               backgroundColor := "yellow",
               cutFruit
             )
             case ((_: String, false), cutFruit: String) => span(cutFruit)
         }
        )

      })


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
