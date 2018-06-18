package com.nekopiano.scala.xml

import scala.xml.pull._
import scala.io.Source

/**
  * Created on 22/07/2017.
  */
object XMLStreaming extends App {

  val src = Source.fromString("<hello><world/></hello>")

  val er = new XMLEventReader(src)


    while (er.hasNext)
      Console.println(er.next)


}
