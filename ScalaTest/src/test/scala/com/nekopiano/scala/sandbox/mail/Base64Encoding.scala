/**
 *
 */
package com.nekopiano.scala.sandbox.mail

/**
 * @author La Musique
 *
 */
object Base64Encoding extends App {

  // http://stackoverflow.com/questions/21318601/how-to-decode-a-base64-string-in-scala-or-java
  //  val enc = "c2VjcmV0UGEkJHdvcmQ="

  //【Morley(検証環境)】
  // case: OK
  //  val enc = "=?UTF-8?B?44CQSy1lbmdpbmUo5qSc6Ki855Kw5aKDKeOAkQ==?="
  // val enc = "44CQSy1lbmdpbmUo5qSc6Ki855Kw5aKDKeOAkQ="

  // case: NOK
  val enc = "44CQSy1lbmdpbmUow6bCpMKc"
  // sunDecoded=【Morley(æ¤

  // val bytes = new sun.misc.BASE64Decoder().decodeBuffer(enc)

  //  val encodedByteArray = enc.getBytes("UTF-8")
  //  import org.apache.commons.codec.binary.Base64
  //  val byteArray = Base64.decodeBase64(encodedByteArray)
  //  val bytes = byteArray.toSeq
  //  val decoded = bytes.map(_.toChar).mkString
  //  println("decoded=" + decoded)

  val sunBytes = new sun.misc.BASE64Decoder().decodeBuffer(enc)
  val sunDecoded = new String(sunBytes, "UTF-8")
  println("sunDecoded=" + sunDecoded)

}
