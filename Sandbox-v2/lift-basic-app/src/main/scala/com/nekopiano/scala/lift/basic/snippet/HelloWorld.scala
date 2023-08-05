package com.nekopiano.scala.lift.basic
package snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import com.nekopiano.scala.lift.basic.lib._
import Helpers._

class HelloWorld {
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
}
