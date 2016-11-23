/**
 *
 */
/**
 * @author La Musique
 *
 */
package com.nekopiano.scala.pdf

import java.io.File
import java.net.URI
import org.apache.pdfbox.util.PDFMergerUtility
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline
import org.apache.pdfbox.pdmodel.PDPage

object Bookmarking {

  val filePath = raw"C:\Repos\acme\y\doc\X1.y_trail50\\usb\y Developer Training Part I\Handouts\"
  val pdfFileNames = Seq(
    "y Developer Training Part I - Core Platform - Module 01 - Warm Up.pdf",
    "y Developer Training Part I - Core Platform - Module 02 - Development Environment Setup.pdf",
    "y Developer Training Part I - Core Platform - Module 03 - Data Modeling.pdf",
    "y Developer Training Part I - Core Platform - Module 04 - hMC.pdf",
    "y Developer Training Part I - Core Platform - Module 05 - PCM Basics.pdf",
    "y Developer Training Part I - Core Platform - Module 06 - Flexible Search.pdf",
    "y Developer Training Part I - Core Platform - Module 07 - Import and Export.pdf",
    "y Developer Training Part I - Core Platform - Module 08 - Programming with the ServiceLayer.pdf",
    "y Developer Training Part I - Core Platform - Module 09 - Java Beans.pdf",
    "y Developer Training Part I - Core Platform - Module 10 - CronJob.pdf",
    "y Developer Training Part I - Core Platform - Module 11 - Cockpit framework.pdf",
    "y Developer Training Part I - Core Platform - Module 12 - Cockpit NG framework.pdf",
    "y Developer Training Part I - Core Platform - Module 13 - Workflows.pdf",
    "y Developer Training Part I - Core Platform - Module 14 - Security.pdf",
    "y Developer Training Part I - Core Platform - Module 15 - Transactions.pdf",
    "y Developer Training Part I - Core Platform - Module 16 - Cache.pdf",
    "y Developer Training Part I - Core Platform - Module 17 - Validation.pdf",
    "y Developer Training Part I - Core Platform - Module 18 - Process Engine.pdf",
    "y Developer Training Part I - Core Platform - Module 19 - Web Services.pdf",
    "y Developer Training Part I - Core Platform - Module 20 - Accelerator.pdf")

  def main(args: Array[String]): Unit = {

    pdfFileNames.foreach(fileName => {
      val readPdf = PDDocument.load(filePath + fileName)

      val item1 = new PDOutlineItem
      val title = fileName.drop(58).dropRight(4).replace(" ", "")
      item1.setTitle((title))
      import scala.collection.JavaConverters._
      val pageList = readPdf.getDocumentCatalog.getAllPages.asScala.toList.asInstanceOf[List[PDPage]]
      println("pagelist.size=" + pageList.size)
      val secondPage = pageList(1)
      item1.setDestination(secondPage)

      val outline = new PDDocumentOutline
      outline.appendChild(item1)
      readPdf.getDocumentCatalog.setDocumentOutline(outline)
      readPdf.save("bookmarked/" + fileName)
      readPdf.close

    })

    //    val utility = new PDFMergerUtility();
    //    pdfFileNames.foreach(fileName => {
    //      utility.addSource((filePath + fileName).replaceAll(" ", "\\ "))
    //    })
    //    utility.setDestinationFileName((filePath + "DeveloperTraining_v5.pdf").replaceAll(" ", "\\ "))
    //    utility.mergeDocuments

    println("Finished.")
  }
}

  
