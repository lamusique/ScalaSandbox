/**
 *
 */
/**
 * @author La Musique
 *
 */
package com.nekopiano.scala.pdf

import scala.collection.JavaConverters._
import org.apache.pdfbox.util.PDFMergerUtility

object Merger {

  val filePath = "C:\\Users\\La Musique\\Documents\\Music\\Rimsky-Orchestration\\"
  val pdfFileNames = List(
 "IMSLP21028-PMLP48692-Rimsky_Osnovy_0Contents.PDF"
,"IMSLP21029-PMLP48692-Rimsky_Osnovy_0Prefaces.PDF"
,"IMSLP21030-PMLP48692-Rimsky_Osnovy_Ch1.PDF"
,"IMSLP21031-PMLP48692-Rimsky_Osnovy_Ch2.PDF"
,"IMSLP21046-PMLP48692-Rimsky_Osnovy_Ch3_English.PDF"
,"IMSLP21048-PMLP48692-Rimsky_Osnovy_Ch4_English.PDF"
,"IMSLP21050-PMLP48692-Rimsky_Osnovy_Ch5_English.PDF"
,"IMSLP21051-PMLP48692-Rimsky_Osnovy_Ch6_English.PDF"
,"IMSLP21055-PMLP48692-Rimsky_Osnovy_Ex000.PDF"
,"IMSLP21056-PMLP48692-Rimsky_Osnovy_Ex001-025.PDF"
,"IMSLP21100-PMLP48692-Rimsky_Osnovy_Ex026-050.PDF"
,"IMSLP21101-PMLP48692-Rimsky_Osnovy_Ex051-075.PDF"
,"IMSLP21102-PMLP48692-Rimsky_Osnovy_Ex076-100.PDF"
  )

  def main(args: Array[String]): Unit = {

    val utility = new PDFMergerUtility();
    pdfFileNames.foreach(fileName => {
      utility.addSource(filePath + fileName)
    })
    utility.setDestinationFileName(filePath + "IMSLP21102-PMLP48692-Rimsky-Orchestration.pdf");
    utility.mergeDocuments();

    //    val document: PDDocument = new PDDocument()
    //
    //    pdfFileNames.foreach(fileName => {
    //      val part = PDDocument.load(filePath + fileName);
    //      //      val pagelist: List[PDPage] = part.getDocumentCatalog().getAllPages().asScala;
    //      val pagelist = part.getDocumentCatalog().getAllPages().asInstanceOf[java.util.List[PDPage]].asScala;
    //      println("pagelist.size=" + pagelist.size)
    //      pagelist.foreach(page => {
    //        document.addPage(page)
    //      })
    //      part.close()
    //    })
    //
    //    document.save(filePath + "merged.pdf")
    //    document.close()

  }
}

  
