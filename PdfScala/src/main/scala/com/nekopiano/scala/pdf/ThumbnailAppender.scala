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
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination
import org.apache.pdfbox.cos.COSBase
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitHeightDestination
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageFitRectangleDestination
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageXYZDestination
import org.apache.pdfbox.pdmodel.interactive.action.`type`.PDActionGoTo

/**
 * @author La Musique
 *
 */
object ThumbnailAppender {

  def main(args: Array[String]): Unit = {
    val pagedrawer = new PageDrawer()

    val srcPdf = PDDocument.load("pdf\\inserted\\Dubois-Theory.pdf")

    // just set default mode rather than appending thumbnails...
    val dc = srcPdf.getDocumentCatalog
        dc.setPageMode(PDDocumentCatalog.PAGE_MODE_USE_THUMBS)

    val openAction = dc.getOpenAction
    println("openAction=" + openAction)
    val dest = new PDPageFitHeightDestination
    //    val dest = new PDPageXYZDestination
    //    dest.setZoom(15)
    //    dest.setLeft(0)
    //    dest.setTop(500)
    // Action activation needs this.
    dest.setPageNumber(0)
    val action = new PDActionGoTo()
    action.setDestination(dest)

    dc.setOpenAction(dest)
    println("dc.getOpenAction=" + dc.getOpenAction)
    //    dc.setOpenAction(new PDPageFitRectangleDestination)

    srcPdf.save("pdf\\thumbnailed\\" + "Dubois-Theory-Thumbnailed.pdf")
    srcPdf.close

    println("Finished")
  }
}
