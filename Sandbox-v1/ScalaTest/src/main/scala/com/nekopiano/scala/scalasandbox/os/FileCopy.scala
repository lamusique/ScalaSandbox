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
object FileCopy {

  // val FOLDER_PATH ="\\\\172.16.100.110\\share\\migdata\\media20130727"
  // val FOLDER_PATH ="C:\\Repos\\AcmeDocsRepo\\git\\datamig\\registeringbinarydata\\registeringbinarydata"
  val FOLDER_PATH = "C:\\Repos\\AcmeDocsRepo\\git\\datamig\\registeringbinarydata_130807"

  def main(args: Array[String]): Unit = {

    System.out.println("Enter a String and press enter.");
    val scan = new Scanner(System.in, "MS932")
    System.out.println("Please enter the absolute path to copy files from.")
    val dirPath = scan.nextLine
    println("dirPath=" + dirPath)

    val sc = new Scanner(new InputStreamReader(
      System.in, Charset.forName("UTF-8")));
    println("Encoding is :" + Charset.defaultCharset())

    println("Enter the path:")
    val inputFilePath = sc.nextLine
    println("Input path:" + inputFilePath)

    //http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
    val files = getFileTree(new File(dirPath)).filter(_.isFile)

    println("files.size=" + files.size)

    files foreach { file =>
      {
        println(file)
        Resource.fromFile(file) copyDataTo Resource.fromFile("medias/" + file.getName)
        // val storingFile = new File("medias/" + file.getName)
        //        val output: Output = Resource.fromFile("medias/" + file.getName)
        //        output.write(file)
      }
    }

    println("Finished.")
  }

  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

}
