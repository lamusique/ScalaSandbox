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
object Replacer {

  def main(args: Array[String]): Unit = {

    val srcPdf = PDDocument.load("pdf\\" + "Reemption.pdf")
    val targetPdf = PDDocument.load("pdf\\" + "File0014.PDF")

    val targetPageNumbers = Seq(52, 54, 56, 58, 60, 64, 66)

    import scala.collection.JavaConverters._
    val srcPageList = srcPdf.getDocumentCatalog.getAllPages.asScala.toSeq.asInstanceOf[Seq[PDPage]]
    println("srcPageList.size=" + srcPageList.size)
    val targetPageList = targetPdf.getDocumentCatalog.getAllPages.asScala.toSeq.asInstanceOf[Seq[PDPage]]
    println("targetPageList.size=" + targetPageList.size)

    val srcPageMap = srcPageList.zipWithIndex.map {
      case (page, i) => targetPageNumbers(i) -> page
    } toMap

    val replaced = targetPageList.zipWithIndex.map {
      case (targetPage, i) => {
        val pageOption = srcPageMap.get(i + 1)
        pageOption match {
          case Some(page) => page
          case None => targetPage
        }
      }
    }

    val newPdf = new PDDocument
    replaced foreach newPdf.addPage _
    newPdf.save("pdf\\replaced\\replacedCP.pdf")
  }

}
