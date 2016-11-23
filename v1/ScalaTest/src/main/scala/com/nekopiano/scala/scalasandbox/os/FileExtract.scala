/**
 *
 */
package com.nekopiano.scala.scalasandbox.os

import java.io.File
import scalax.io.Resource
import scalax.io.Output
import java.util.Scanner
import java.nio.charset.Charset
import java.io.InputStreamReader

/**
 * @author La Musique
 *
 */
object FileExtract {

  val TARGET_PATHS = Seq(
    raw"bin\custom\samplecore\resources\samplecore-items.xml",
    raw"bin\custom\samplecore\src\jp\sample\y\core\jalo\AcmecoreManager.java",
    raw"bin\custom\sampleproductcockpit\resources\sampleproductcockpit\sampleproductcockpit-spring-services.xml",
    raw"bin\custom\sampleproductcockpit\resources\sampleproductcockpit\sampleproductcockpit-web-spring.xml",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\services\meta\impl\ProductReferenceSpecNotePropertyValueHandler.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\services\meta\impl\SpecNotePropertyValueHandler.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\session\editor\AttributeSpecNoteUIEditor.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\session\editor\RichTextSpecNoteUIEditor.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\session\editor\SectionSpecNoteUIEditor.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\session\editor\SpecNoteUIEditor.java",
    raw"bin\custom\sampleproductcockpit\src\jp\sample\y\productcockpit\session\impl\AcmeNewItemSection.java")

  def main(args: Array[String]): Unit = {
    System.out.println("Enter a String and press enter.");
    val scan = new Scanner(System.in, "MS932")
    System.out.println("Please enter the absolute path to copy files from.")
    val dirPath = scan.nextLine
    println("dirPath=" + dirPath)

    val files = getFileTree(new File(dirPath)).filter(_.isFile)

    println("files.size=" + files.size)

    val targetFiles = files.map(file =>
      {
        val absoluthPath = file.getAbsolutePath
        TARGET_PATHS.collectFirst { case targetPath if (absoluthPath.contains(targetPath)) => file }
      }).collect { case Some(file) => file }

    println("targetFiles.size=" + targetFiles.size)

    targetFiles foreach (file =>
      {
        val absoluthPath = file.getAbsolutePath
        println("file=" + file)
        Resource.fromFile(file) copyDataTo Resource.fromFile("extracted/" + file.getName)
      })

    println("Finished.")
  }

  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

}
