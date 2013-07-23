/**
 *
 */
package com.nekopiano.scala.sandbox

import org.joda.time.format.ISODateTimeFormat

/**
 * @author La Musique
 *
 */
object IsoDateTimeTest {

  def main(args: Array[String]): Unit = {

    // OK
    parse("2013-05-31T13:32:31+08:00")
    parse("20113-06-07T13:32:31+09:00")
    parse("22001133-06-07T13:32:31+09:00")
    // NG
    // parse("22001133-106-07T13:32:31+09:00")

  }

  def parse(utcDateTime: String) {
    import com.github.nscala_time.time.Imports._

    // nscala test, which is a wrapper for Joda-Time
    val XML_DATE_TIME_FORMAT = ISODateTimeFormat.dateTimeNoMillis()

    // throw IllegalArgumentException
    val dt = XML_DATE_TIME_FORMAT.parseDateTime(utcDateTime)
    val date = dt.toDate
    println("date=" + date)
  }

}
