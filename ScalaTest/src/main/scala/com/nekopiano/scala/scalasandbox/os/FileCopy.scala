/**
 *
 */
package com.nekopiano.scala.scalasandbox.os

import java.io.File
import scalax.io.Resource
import scalax.io.Output

/**
 * @author La Musique
 *
 */
object FileCopy {

  def main(args: Array[String]): Unit = {
    //http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
    val files = getFileTree(new File("\\\\172.16.100.110\\share\\migdata\\media20130727")).filter(_.isFile)

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
  }

  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

}
