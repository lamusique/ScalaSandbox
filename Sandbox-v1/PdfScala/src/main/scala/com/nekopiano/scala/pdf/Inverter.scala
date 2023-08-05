/**
 *
 */
package com.nekopiano.scala.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage

/**
 * @author La Musique
 *
 */
object Inverter {

  val pdfFileNames = Seq(
    "Ikenouchi-CP-NotInverted.pdf")

  def main(args: Array[String]): Unit = {

    val invertingPageNumbers = (19 to 22) ++ (89 to 93) ++ (97 to 101) ++ (105 to 109) ++ (112 to 116) ++ (119 to 123) ++ (126 to 130) ++ (133 to 135) ++ (140 to 142) ++ (145 to 149)

    import scala.collection.JavaConverters._

    pdfFileNames foreach (pdfFileName => {
      val readPdf = PDDocument.load("pdf\\" + pdfFileName)
      val pageList = readPdf.getDocumentCatalog.getAllPages.asScala.toList.asInstanceOf[List[PDPage]]
      println("pagelist.size=" + pageList.size)
      val newPdf = new PDDocument
      pageList.zipWithIndex.foreach {
        case (page, i) => {
          if (invertingPageNumbers.contains(i + 1)) page.setRotation(180)
          newPdf.addPage(page)
        }
      }
      newPdf.save("pdf\\inverted\\" + pdfFileName)
      newPdf.close
    })

    println("Finished.")
  }

}
