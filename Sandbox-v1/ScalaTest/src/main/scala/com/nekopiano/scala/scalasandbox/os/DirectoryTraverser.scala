/**
 *
 */
package com.nekopiano.scala.scalasandbox.os

import java.io.File

/**
 * @author La Musique
 *
 */
object DirectoryTraverser {

  val DIR_PATH = "C:\\Users\\La Musique\\Pictures"

  def main(args: Array[String]): Unit = {
    andTree(new File(DIR_PATH)).foreach(f => println(">> " + f))
  }
  def andTree(dir: File): List[File] = {
    val fileList = dir.listFiles.toList
    (fileList filter (f => f.isFile && f.getName.endsWith(".jpg"))) :::
      (fileList filter (f => f.isDirectory) flatMap (f => andTree(f)))
  }
}
