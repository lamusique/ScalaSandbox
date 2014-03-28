/**
 *
 */
package com.nekopiano.scala.scalasandbox.string

import java.util.Scanner

/**
 * @author La Musique
 *
 */
object Camel2Snake extends App {

  util.Properties.setProp("scala.time", "Camel2Snake")
  
  // for database column name

  //  System.out.println("Please give number");
  //  val s = new Scanner(System.in)
  //  val number = s.nextLine
  //  System.out.println("You typed " + number);

  val camels = """
makerCode
makerName
divisionCode
name
description
categoryId
catalogCode
detailUrl
priceRange
imagePath
deleteFlg
createDate
lastUpdateDate
importDate
""".split("\n")

  camels.foreach(s => {
    println(camelToUnderscores(s).toUpperCase)
  })

  /**
   * Takes a camel cased identifier name and returns an underscore separated
   * name
   *
   * Example:
   *     camelToUnderscores("thisIsA1Test") == "this_is_a_1_test"
   */
  def camelToUnderscores(name: String) = "[A-Z\\d]".r.replaceAllIn(name, { m =>
    "_" + m.group(0).toLowerCase()
  })

  /* 
 * Takes an underscore separated identifier name and returns a camel cased one
 *
 * Example:
 *    underscoreToCamel("this_is_a_1_test") == "thisIsA1Test"
 */

  def underscoreToCamel(name: String) = "_([a-z\\d])".r.replaceAllIn(name, { m =>
    m.group(1).toUpperCase()
  })

}
