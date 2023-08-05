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
object CheckPdf {
  val pdfFileNames = Seq(
    "Cover Page.pdf", "Evenly numbered pages (1).pdf", "Evenly numbered pages (2).pdf", "Evenly numbered pages (3).pdf", "Oddly numbered pages (1).pdf", "Oddly numbered pages (2).pdf", "Oddly numbered pages (3).pdf", "Oddly numbered pages (4).pdf", "Oddly numbered pages (5).pdf", "Oddly numbered pages (6).pdf", "Oddly numbered pages (7).pdf", "Oddly numbered pages (8).pdf", "Pages (68) and (92).pdf")

  def main(args: Array[String]): Unit = {
    import scala.collection.JavaConverters._

    pdfFileNames foreach (pdfFileName => {
      val readPdf = PDDocument.load("pdf\\" + pdfFileName)
      val pageList = readPdf.getDocumentCatalog.getAllPages.asScala.toList.asInstanceOf[List[PDPage]]
//      print("name=" + pdfFileName)
//      print(",")
//      println("pagelist.size=" + pageList.size)
      println(pdfFileName + '\t' + pageList.size)
    })
  }

}
