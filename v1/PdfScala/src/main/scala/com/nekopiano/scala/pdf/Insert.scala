/**
 *
 */
package com.nekopiano.scala.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import javax.imageio.ImageIO

/**
 * @author La Musique
 *
 */
object Insert {

  def main(args: Array[String]): Unit = {
    import scala.collection.JavaConverters._

    //    val srcPdf = PDDocument.load("pdf\\" + "Dubois-Theory-p22.pdf")
    //    val targetPdf = PDDocument.load("pdf\\" + "Dubois-Theory-inverted-2.pdf")

    val srcPdf = PDDocument.load("pdf\\" + "Dubois-Theory-p22.pdf")
    val targetPdf = PDDocument.load("pdf\\" + "Dubois-Theory-inverted-2.pdf")

    val srcPageList = srcPdf.getDocumentCatalog.getAllPages.asScala.toSeq.asInstanceOf[Seq[PDPage]]
    println("srcPageList.size=" + srcPageList.size)
    val targetPageList = targetPdf.getDocumentCatalog.getAllPages.asScala.toSeq.asInstanceOf[Seq[PDPage]]
    println("targetPageList.size=" + targetPageList.size)

    //    val targetNumbering = Seq(60, 62, 64, 66, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 94, 96, 98)
    // total 305 pages
    val targetNumbering = (1 to 23) ++ (25 to 306) toSeq

    println(targetNumbering)

    val numberedTarget = for (i <- 0 until targetNumbering.size) yield {
      targetNumbering(i) -> targetPageList(i)
    }
    //    val numberedSrc = Seq(68 -> srcPageList(0), 92 -> srcPageList(1))
    val numberedSrc = Seq(24 -> srcPageList(0))
    val merged = numberedSrc ++ numberedTarget
    val ordered = merged.sortBy(_._1).map(_._2)

    val newPdf: PDDocument = new PDDocument()
    ordered foreach (page => {
      newPdf.addPage(page)
    })

    //    newPdf.save("pdf\\inserted\\" + "Evenly numbered pages (3).pdf")
    newPdf.save("pdf\\inserted\\" + "Dubois-Theory.pdf")
    newPdf.close

    println("Finished.")
  }

}
