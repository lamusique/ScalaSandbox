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
object SpecificInverter {

  //  val pdfFilePath = raw"C:\Users\La Musique\Pictures\Toolbox\デュボア-和声学理論編\edit\merged1.pdf"
  val pageNumbers = Seq(21, 35, 57, 61, 63, 69, 71, 73, 95, 117, 127, 129, 131)

  def main(args: Array[String]): Unit = {

    import scala.collection.JavaConverters._

    val pdfFileName = "Dubois-Realization.pdf"
    val readPdf = PDDocument.load("pdf\\" + pdfFileName)
    val pageList = readPdf.getDocumentCatalog.getAllPages.asScala.toList.asInstanceOf[List[PDPage]]
    println("pagelist.size=" + pageList.size)
    val newPdf = new PDDocument
    pageList.zipWithIndex.foreach {
      case (page, i) => {
        // -15 doesn't work...
        if (pageNumbers.contains(i + 1)) page.setRotation(180)
        newPdf.addPage(page)
      }
    }
    newPdf.save("pdf\\inverted\\" + pdfFileName)
    newPdf.close
    println("Finished.")
  }

}
