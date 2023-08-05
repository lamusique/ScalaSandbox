/**
 *
 */
/**
 * @author La Musique
 *
 */
package com.nekopiano.scala.pdf

import org.apache.pdfbox.util.PDFMergerUtility

object Merger {

  val filePath = "pdf\\"
  val pdfFileNames = Seq("Ikenouchi-CP1.PDF",
    "Ikenouchi-CP2.PDF",
    "Ikenouchi-CP3.PDF",
    "Ikenouchi-CP4.PDF",
    "Ikenouchi-CP5.pdf",
    "Ikenouchi-CP6.PDF")

  def main(args: Array[String]): Unit = {

    val utility = new PDFMergerUtility();
    pdfFileNames.foreach(fileName => {
      utility.addSource(filePath + fileName)
    })
    utility.setDestinationFileName(filePath + "Ikenouchi-CP.pdf")
    utility.mergeDocuments

    println("Finished.")
  }
}

  
