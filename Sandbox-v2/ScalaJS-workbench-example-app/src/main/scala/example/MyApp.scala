package example

import org.scalajs.dom.document
import scalatags.JsDom.all._
import CssSettings._
import org.scalajs.dom.raw.HTMLStyleElement
import scalacss.ScalatagsCss._
import scalatags.JsDom.TypedTag
//import scalatags.Text._
//import scalatags.Text.all._


import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}

@JSExportTopLevel("example.MyApp")
object MyApp {
  @JSExport
  def renderMyPage() = {

    val body = document.getElementsByTagName("body").item(0)
    //body.appendChild(p(myPage.render))

    body.appendChild(
      div(
        myPage
      ).render
    )


  }

  def myPage =
    div(
      //MyStyles.render[TypedTag[String]],
      //MyStyles.render[scalatags.Text.TypedTag[String]],
      MyStyles.render[scalatags.JsDom.TypedTag[HTMLStyleElement]],
      div(
        button(
          MyStyles.bootstrapButton,
          "I am a button!"
        )
      )
    )

  def myPageB =
    div(
      h1("Search Box!"),
      p(
        "Type here to filter " +
          "the list of things below!"
      ),
      div("a div")
    ).render

}
