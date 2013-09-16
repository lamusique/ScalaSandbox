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
  val pageNumbers = Seq(13, 19, 77, 125, 204, 270, 289, 298, 299)

  def main(args: Array[String]): Unit = {

    import scala.collection.JavaConverters._

    val pdfFileName = "Dubois-Theory-inverted-1.pdf"
    val readPdf = PDDocument.load("pdf\\" + pdfFileName)
    val pageList = readPdf.getDocumentCatalog.getAllPages.asScala.toList.asInstanceOf[List[PDPage]]
    println("pagelist.size=" + pageList.size)
    val newPdf: PDDocument = new PDDocument()
    pageList.zipWithIndex.foreach {
      case (page, i) => {
        // -15 doesn't work...
        if (pageNumbers.contains(i + 1)) page.setRotation(180)
        newPdf.addPage(page)
      }
    }
    newPdf.save("pdf\\inverted\\" + "Dubois-Theory-inverted.pdf")
    newPdf.close
    println("Finished.")
  }

}
