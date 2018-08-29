package example
import scala.scalajs.js.annotation.{JSExport, JSExportTopLevel}
import org.scalajs.dom
import org.scalajs.dom.html

import scala.util.Random

case class Point(x: Int, y: Int){
  def +(p: Point) = Point(x + p.x, y + p.y)
  def /(d: Int) = Point(x / d, y / d)
}

//@JSExport
//Warning:(12, 2) @JSExport on objects is deprecated and will be removed in 1.0.0. Use @JSExportTopLevel instead. Note that it exports the object itself (rather than a 0-arg function returning the object), so the calling JavaScript code must be adapted.
//(you can suppress this warning in 0.6.x by passing the option `-P:scalajs:suppressExportDeprecations` to scalac)
//@JSExport
@JSExportTopLevel("ScalaJSExample")
object ScalaJSExample {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val ctx = canvas.getContext("2d")
                    .asInstanceOf[dom.CanvasRenderingContext2D]

    var count = 0
    var p = Point(0, 0)
    val corners = Seq(Point(255, 255), Point(0, 255), Point(128, 0))

    def clear() = {
      //ctx.fillStyle = "black"
      ctx.fillStyle = "skyblue"
      ctx.fillRect(0, 0, 255, 255)
    }

    def run = for (i <- 0 until 10){
      if (count % 3000 == 0) clear()
      count += 1
      p = (p + corners(Random.nextInt(3))) / 2

      val height = 512.0 / (255 + p.y)
      val r = (p.x * height).toInt
      val g = ((255-p.x) * height).toInt
      val b = p.y
      ctx.fillStyle = s"rgb($g, $r, $b)"

      ctx.fillRect(p.x, p.y, 1, 1)
    }

    dom.window.setInterval(() => run, 50)
  }
}
