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
object Interleaving {
  val pdfFileNames = Seq(
    "Cover Page.pdf", "Evenly numbered pages (1).pdf", "Evenly numbered pages (2).pdf", "Evenly numbered pages (3).pdf", "Oddly numbered pages (1).pdf", "Oddly numbered pages (2).pdf", "Oddly numbered pages (3).pdf", "Oddly numbered pages (4).pdf", "Oddly numbered pages (5).pdf", "Oddly numbered pages (6).pdf", "Oddly numbered pages (7).pdf", "Oddly numbered pages (8).pdf", "Pages (68) and (92).pdf")

  def main(args: Array[String]): Unit = {
    import scala.collection.JavaConverters._

    val evenPdfs = pdfFileNames.filter(_.startsWith("Evenly"))
    val oddPdfs = pdfFileNames.filter(_.startsWith("Oddly")).filterNot(_.contains("(7)")).filterNot(_.contains("(8)"))

    def createPages(names: Seq[String]) = {
      names.map(pdfName => {
        val pdf = PDDocument.load("pdf\\" + pdfName)
        pdf.getDocumentCatalog.getAllPages.asScala.toSeq.asInstanceOf[Seq[PDPage]]
      }).flatten
    }
    val evenSeq = createPages(evenPdfs)
    println("evenSeq.size=" + evenSeq.size)
    val oddSeq = createPages(oddPdfs)
    println("oddSeq.size=" + oddSeq.size)

    def numberByTwo(startNumber: Int, pageSeq: Seq[org.apache.pdfbox.pdmodel.PDPage]) = {
      var index = startNumber - 2
      pageSeq.map(pdf => {
        index += 2
        (index, pdf)
      })
    }
    val numberedEvenSeq = numberByTwo(4, evenSeq)
    val numberedOddSeq = numberByTwo(3, oddSeq)

    val sorted = (numberedEvenSeq ++ numberedOddSeq).sortBy(_._1).map(_._2)
    println("sorted.size=" + sorted.size)

    val newPdf: PDDocument = new PDDocument()
    sorted.foreach(page => {
      newPdf.addPage(page)
    })
    newPdf.save("pdf\\interleaved\\" + "interleaved(3-99).pdf")
    newPdf.close

  }

}
