/**
 *
 */
package com.nekopiano.scala.pdf

import org.apache.pdfbox.pdfviewer.PageDrawer
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.PDPage
import javax.imageio.ImageIO
import org.apache.pdfbox.pdmodel.common.PDMetadata
import org.apache.pdfbox.pdmodel.PDDocumentCatalog

/**
 * @author La Musique
 *
 */
object ThumbnailAppender {

  def main(args: Array[String]): Unit = {
    val pagedrawer = new PageDrawer()

    val srcPdf = PDDocument.load("pdf\\inserted\\Dubois-Theory.pdf")
    
    // just set default mode rather than appending thumbnails...
    srcPdf.getDocumentCatalog.setPageMode(PDDocumentCatalog.PAGE_MODE_USE_THUMBS)

    srcPdf.save("pdf\\thumbnailed\\" + "Dubois-Theory-Thumbnailed.pdf")
    srcPdf.close

    println("Finished")
  }
}
