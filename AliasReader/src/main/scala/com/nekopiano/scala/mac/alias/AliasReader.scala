package com.nekopiano.scala.mac.alias

import java.nio.charset.Charset

import better.files._
import better.files.Dsl._   // must import Dsl._ to bring in these utils

object AliasReader extends App {

  val file = cwd / "test.txt alias"
  println("file=" + file)

  //val byteArray = Files.readAllBytes(Paths.get("/path/to/file"))
  val bytes = file.byteArray

  val text = new String(bytes, Charset.forName("UTF-8"))
  println("text=" + text)
  text.map(c => {
    val hex = byte2hex(c.toByte)
    println(c + ": " + hex)
  })

  val hexes = bytes2hex(bytes, " ")
  println("hexes=" + hexes)

  def byte2hex(byte: Byte): String = "%02x".format(byte)
  def bytes2hex(bytes: Array[Byte], sep: String = ""): String = bytes.map("%02x".format(_)).mkString(sep)

  def hex2bytes(hex: String): Array[Byte] = {
    hex.replaceAll("[^0-9A-Fa-f]", "").sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)
  }

  val separatorSeq = "08 00 00 00 01 01 00 00"
  val bytesFromHexes = hex2bytes(separatorSeq)

  val replaced = hexes.replace("00 00 00 01 01 00 00", "2F").replace(" 00", "").replace(" 08", "")
  val replacedText = new String(hex2bytes(replaced), Charset.forName("UTF-8"))
  println("replacedText=" + replacedText)


}
