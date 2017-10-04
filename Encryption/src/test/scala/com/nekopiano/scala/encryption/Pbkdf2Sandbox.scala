package com.nekopiano.scala.encryption

import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

object Pbkdf2Sandbox extends App {

  //val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
  val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
  //val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA3")

//  val encoded = getEncryptedPassword("abcd0123", null, 1, 1)
//  println(encoded)

//  val plainSpec = new PBEKeySpec("abcd0123".toCharArray)
//  val plainEncoded = skf.generateSecret(plainSpec).getEncoded
//  println("plainEncoded=" + plainEncoded)
//  //Exception in thread "main" java.security.spec.InvalidKeySpecException: Salt not found

  val originalPassword = "password"
  val generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword)
  System.out.println(generatedSecuredPasswordHash)



  import javax.crypto.SecretKeyFactory
  import javax.crypto.spec.PBEKeySpec
  import java.security.NoSuchAlgorithmException
  import java.security.spec.InvalidKeySpecException

  @throws[NoSuchAlgorithmException]
  @throws[InvalidKeySpecException]
  private def generateStorngPasswordHash(password: String) = {
    val iterations = 10000
    val chars = password.toCharArray
    //val salt = getSalt
    //val spec = new PBEKeySpec(chars, salt, iterations, 64 * 8)

    // salt
    val salt = toBytes("6b7a5c3f52401d610ffad53e478663d3")

    // 32 digits
    val spec = new PBEKeySpec(chars, salt, iterations, 64 * 2)

    val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
    val hash = skf.generateSecret(spec).getEncoded

    val saltHex = toHex(salt)
    val hashHex = toHex(hash)

    println("saltHex.size=" + saltHex.size)
    println("hashHex.size=" + hashHex.size)

    iterations + ":" + toHex(salt) + ":" + toHex(hash)
    //iterations + "$" + toHex(salt) + "$" + toHex(hash)
  }



  import java.security.NoSuchAlgorithmException
  import java.security.SecureRandom

  @throws[NoSuchAlgorithmException]
  private def getSalt = {
    val sr = SecureRandom.getInstance("SHA1PRNG")
    val salt = new Array[Byte](16)
    sr.nextBytes(salt)
    salt
  }


  import javax.crypto.SecretKeyFactory
  import javax.crypto.spec.PBEKeySpec
  import java.security.NoSuchAlgorithmException
  import java.security.spec.InvalidKeySpecException
  import java.security.spec.KeySpec

  @throws[NoSuchAlgorithmException]
  @throws[InvalidKeySpecException]
  def getEncryptedPassword(password: String, salt: Array[Byte], iterations: Int, derivedKeyLength: Int) = {
    val spec = new PBEKeySpec(password.toCharArray, salt, iterations, derivedKeyLength * 8)
    val f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    f.generateSecret(spec).getEncoded
  }


  import java.math.BigInteger
  import java.security.NoSuchAlgorithmException

  @throws[NoSuchAlgorithmException]
  private def toHex(array: Array[Byte]) = {
    val bi = new BigInteger(1, array)
    val hex = bi.toString(16)
    val paddingLength = (array.length * 2) - hex.length
    //if (paddingLength > 0) String.format("%0" + paddingLength.toString + "d", 0) + hex
    if (paddingLength > 0) ("%0" + paddingLength.toString + "d").format(0) + hex
    else hex
  }

  private def toBytes(hex:String) = {
    hex.replaceAll("[^0-9A-Fa-f]", "").sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)
  }


}

object HexBytesUtil {

  def hex2bytes(hex: String): Array[Byte] = {
    hex.replaceAll("[^0-9A-Fa-f]", "").sliding(2, 2).toArray.map(Integer.parseInt(_, 16).toByte)
  }

  def bytes2hex(bytes: Array[Byte], sep: Option[String] = None): String = {
    sep match {
      case None => bytes.map("%02x".format(_)).mkString
      case _ => bytes.map("%02x".format(_)).mkString(sep.get)
    }
    // bytes.foreach(println)
  }

  def example {
    val data = "48 65 6C 6C 6F 20 57 6F 72 6C 64 21 21"
    val bytes = hex2bytes(data)
    println(bytes2hex(bytes, Option(" ")))

    val data2 = "48-65-6C-6C-6F-20-57-6F-72-6C-64-21-21"
    val bytes2 = hex2bytes(data2)
    println(bytes2hex(bytes2, Option("-")))

    val data3 = "48656C6C6F20576F726C642121"
    val bytes3 = hex2bytes(data3)
    println(bytes2hex(bytes3))
  }

}
