/**
 *
 */
package com.nekopiano.scala.rest

import scala.io.Source
import javax.xml.validation.SchemaFactory
import org.xml.sax.SAXException
import javax.xml.transform.stream.StreamSource
import java.io.File

/**
 * @author La Musique
 *
 */
object XmlValidator {

  val DIR = "C:\\Repos\\AcmeDocsRepo\\git\\単体テスト\\REST\\1.ExpectedResponsesFromExcelSpec\\"
  val DIR_URI = "file://C:/Repos/AcmeDocsRepo/git/単体テスト/REST/1.ExpectedResponsesFromExcelSpec/"

  def main(args: Array[String]): Unit = {

    // val s = Source.fromFile("requests/REST-requests.txt")
    //    val xsd = Source.fromFile("C:\\Repos\\AcmeDocsRepo\\git\\単体テスト\\REST\\1.ExpectedResponsesFromExcelSpec\\1-ApprovedList.xsd")

    //require(args.size >= 2, "Params: xmlFile, xsdFile")

    val result =
      //      if (validate(args(0), args(1)))
//      if (validate(new File(DIR + "1-ApprovedList.xml"), new File(DIR + "1-ApprovedList.xsd")))
      if (validate(new File("responses/payloads/1.ApprovedList.xml"), new File(DIR + "1-ApprovedList.xsd")))
        "Validates!"
      else
        "Not valid."
    println(result)

  }

  def validate(xmlFile: File, xsdFile: File): Boolean = {
    try {
      val schemaLang = "http://www.w3.org/2001/XMLSchema"
      val factory = SchemaFactory.newInstance(schemaLang)
      val schema = factory.newSchema(new StreamSource(xsdFile))
      val validator = schema.newValidator()
      validator.validate(new StreamSource(xmlFile))
    } catch {
      case ex: SAXException =>
        throw ex; println(ex); return false
      case ex: Exception => ex.printStackTrace()
    }
    true
  }

  def validate(xmlFile: String, xsdFile: String): Boolean = {
    validate(new File(xmlFile), new File(xsdFile))
  }

}
