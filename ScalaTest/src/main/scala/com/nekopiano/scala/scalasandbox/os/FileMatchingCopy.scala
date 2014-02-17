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
object FileMatchingCopy {

  def main(args: Array[String]): Unit = {

    //    System.out.println("Enter a String and press enter.");
    //    val scan = new Scanner(System.in, "MS932")
    //    System.out.println("Please enter the absolute path to search files from.")
    //    val dirPath = scan.nextLine
    //    println("dirPath=" + dirPath)

    val targetDirPath = raw"C:\Users\La Musique\Copy\23.Sounds\Radio"
    val srcDirPath = raw"C:\Users\La Musique\Dropbox\31.Shared\Radio"

    val dirPath = targetDirPath

    //http://stackoverflow.com/questions/2637643/how-do-i-list-all-files-in-a-subdirectory-in-scala
    val dirs = getFileTree(new File(dirPath)).filter(_.isDirectory)

    dirs foreach { file =>
      {
        println(file.getName)
        //        Resource.fromFile(file) copyDataTo Resource.fromFile("medias/" + file.getName)
        // val storingFile = new File("medias/" + file.getName)
        //        val output: Output = Resource.fromFile("medias/" + file.getName)
        //        output.write(file)
      }
    }

    println("dirs.size=" + dirs.size)

    //    val files = getFileTree(new File(srcDirPath)).filter(_.isFile)
    val files = getTree(new File(srcDirPath), { _.isFile })
    files foreach { file =>
      {
        val fileName = file.getName

        val matchedDirs = dirs.filter(dir => {
          val dirName = dir.getName.split("-").head
          fileName.startsWith(dirName)
        })

        matchedDirs.size match {
          case 0 => {
            print("Not matched: ")
            print("fileName=" + fileName)
            print(", ")
            println("matchedDirs=" + matchedDirs)
          }
          case 1 => {
            val matchedDir = matchedDirs.head
            println("result: file=" + file + ", target=" + matchedDir.getAbsolutePath)
            //Resource.fromFile(file) copyDataTo Resource.fromFile(matchedDir.getAbsolutePath + '\\' + file.getName)
          }
          case _ => {
            print("Unexpected duplicated directories: ")
            print("fileName=" + fileName)
            print(", ")
            println("matchedDirs=" + matchedDirs)
            throw new Exception("Unexpected duplicated directories!")
          }
        }

      }
    }

    println("Finished.")
  }

  def getTree(f: File, fs: File => Boolean) = {
    getFileTree(f).filter(fs)
  }

  def getFileTree(f: File): Stream[File] =
    f #:: (if (f.isDirectory) f.listFiles().toStream.flatMap(getFileTree)
    else Stream.empty)

}
