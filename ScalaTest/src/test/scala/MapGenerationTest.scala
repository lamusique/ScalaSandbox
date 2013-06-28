/**
 *
 */
/**
 * @author La Musique
 *
 */
import scala.annotation.tailrec

object MapGenerationTest {

  def main(args: Array[String]): Unit = {

    val list = List(List("TypeA", null, null), List(null, "A", "あ"), List(null, "B", "い"), List("TypeB", null, null), List(null, "C", "う"))

    @tailrec
    def genMap(defList: List[List[String]], enumDefMap: Map[String, List[Map[String, String]]], enumtypeNameSpecified: String = null): Map[String, List[Map[String, String]]] = {

      if (defList == Nil) {
        enumDefMap
      } else {
        val defRow = defList.head

        val writtenEnumtypeNameCell = defRow(0)
        val enumtypeName =
          if (writtenEnumtypeNameCell == null) enumtypeNameSpecified
          else writtenEnumtypeNameCell

        val enumtypeValue = defRow(1)
        val enumtypeTitleName = defRow(2)

        val oneTypeList = if (enumDefMap.keySet.exists(_ == enumtypeName)) enumDefMap(enumtypeName) else List.empty
        //        println("oneTypeMap=" + oneTypeMap)
        val newValueMap = Map("enumtypeValue" -> enumtypeValue, "enumtypeTitleName" -> enumtypeTitleName)
        val mergedList = newValueMap :: oneTypeList
        genMap(defList.tail, enumDefMap + (enumtypeName -> mergedList), enumtypeName)
      }

    }

    // list needs to be reversed.
    val map = genMap(list, Map.empty)
    println(map)

  }

}
