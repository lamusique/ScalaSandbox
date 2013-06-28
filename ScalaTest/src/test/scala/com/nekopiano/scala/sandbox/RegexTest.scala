/**
 *
 */
package jp.sample.scala

/**
 * @author La Musique
 *
 */
object RegexTest {

  def main(args: Array[String]): Unit = {

    val s = "MACH方式※1/5,760×1,440dpi※12"
    val r = "※[0-9]+".r
    val data = r.findAllIn(s).matchData
    println("data=" + data.mkString)
    val noteList = data.map(e => {
      //println(e)
      val noString = e.toString.replace("※", "")
      noString.toInt
    }).toList
    println(noteList)

    val descriptionSplits = s.split("※[0-9]+").toList
    descriptionSplits.foreach(e => println("split=" + e))

    val seq = noteList.zipWithIndex
    seq.foreach {
      case (e, i) => {
        print(descriptionSplits(i) + "<note=" + e + ">")
      }
    }
    println()

    for (m <- data)
      println("Found a match in [%s, %s)".format(m.start, m.end))

    val replaced = s.replaceAll("※[0-9]+", "<NoteCode>")
    println(replaced)
  }

}
