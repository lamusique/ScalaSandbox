/**
 *
 */
package com.nekopiano.scala.scalaxb.y.items

import scala.annotation.implicitNotFound

/**
 * @author La Musique
 *
 */
object ItemsTraverser extends App {

  util.Properties.setProp("scala.time", "")

  val xml = scala.xml.XML.loadFile("xml/samplecore-items.xml")
  val items = scalaxb.fromXML[Items](xml)

  // ==== Relations

  val optionalRelationType = items.relations
  //  println("optionalRelationTypes=" + optionalRelationTypes)
  // Some(RelationsType(List(RelationType(List(DataRecord, ...)), RelationType(...

  val relationGraphVizString = optionalRelationType.get.relation map (relationType => {
    println("relationType.code=" + relationType.code)
    val relationMap = relationType.mixed map (dataRecord => {
      //      println("dataRecord.key=" + dataRecord.key)
      //      println("dataRecord.value=" + dataRecord.value)

      val relationTuple = dataRecord.key match {
        case Some("sourceElement") =>
          "sourceElement.type" -> dataRecord.value.asInstanceOf[RelationElementType].typeValue
        case Some("targetElement") => "targetElement.type" -> dataRecord.value.asInstanceOf[RelationElementType].typeValue
        case None => "" -> ""
      }
      relationTuple
    }) toMap

    //    val relationGraphViz = relationMap("sourceElement.type") + " -> " + relationMap("targetElement.type")
    // normally one2many so should be reversed...
    val relationGraphViz = relationMap("targetElement.type") + " -> " + relationMap("sourceElement.type") + " [style=dotted]"
    println("relationGraphViz=" + relationGraphViz)
    relationGraphViz
  })

  // ==== Items
  val optionalItemtype = items.itemtypes
  //println("optionalItemtype=" + optionalItemtype)
  // Some(ItemtypesType(List(ItemtypeType(List(DataRecord(),DataRecord(attributes,AttributeType(),...

  val extendsGraphVizString = optionalItemtype.get.itemtype map (itemtypeType => {
    println("itemtypeType.code=" + itemtypeType.code)
    val extendsValue = itemtypeType.extendsValue match {
      case Some(extendsV) => {
        println("extends=" + extendsV)
        itemtypeType.code + " -> " + extendsV + " [label=\"extends\"]"
      }
      case None => ""
    }

    itemtypeType.mixed foreach (dataRecord => {
      dataRecord.key match {
        case Some("attributes") => {
          val attributesType = dataRecord.value.asInstanceOf[AttributesType]
          val attributes = attributesType.attribute
          attributes foreach (attribute => {
            println("attribute.qualifier=" + attribute.qualifier)
            // val unique = attribute.modifiers.getOrElse(false).unique.getOrElse(false)
            attribute.modifiers match {
              case Some(ModifiersType(_, _, _, _, _, _, _, _, Some(true), _, _)) => println("this is unique.")
              case Some(others) => {}
              case None => {}
            }
          })
        }
        case Some(something) => println("something=" + something)
        case None => println("None...")
      }
    })

    extendsValue
  })

  println("// " + "=" * 50)
  println("// relationGraphViz")
  println(relationGraphVizString.mkString("\n"))
  println("// " + "=" * 50)
  println("// extendsGraphViz")  
  println(extendsGraphVizString.filterNot(_.isEmpty).mkString("\n"))
}
