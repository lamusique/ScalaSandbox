/**
 *
 */
package com.nekopiano.scala.scalaxb.y.items

import scala.annotation.implicitNotFound
import scala.collection.mutable.HashMap

/**
 * @author La Musique
 *
 */
object ItemsXMLTraverser extends App {

  util.Properties.setProp("scala.time", "")

  // val xml = scala.xml.XML.loadFile("xml/samplecore-items.xml")

  // from C:\Repos\apps\yootb\bin\platform\ext\core\resources\core-items.xml  
  // val xml = scala.xml.XML.loadFile("xml/core-items.xml")

  val xml = scala.xml.XML.loadFile("xml/catalog-items.xml")

  val items = scalaxb.fromXML[Items](xml)

  // ==== Relations

  val optionalRelationType = items.relations
  // println("optionalRelationTypes=" + optionalRelationTypes)
  // Some(RelationsType(List(RelationType(List(DataRecord, ...)), RelationType(...

  val relationMaps = optionalRelationType.get.relation map (relationType => {
    println("relationType.code=" + relationType.code)
    val relationMap = relationType.mixed map (dataRecord => {
      //      println("dataRecord.key=" + dataRecord.key)
      //      println("dataRecord.value=" + dataRecord.value)

      val relationTuple = dataRecord.key match {
        case Some("sourceElement") =>
          "sourceElement" -> {
            val relation = dataRecord.value.asInstanceOf[RelationElementType]
            relation.typeValue -> relation.qualifier.get
          }
        case Some("targetElement") => "targetElement" -> {
          val relation = dataRecord.value.asInstanceOf[RelationElementType]
          relation.typeValue -> relation.qualifier.get
        }
        // ignore
        case Some("deployment") => "" -> ("" -> "")
        case None => "" -> ("" -> "")
      }
      relationTuple
    }) toMap

    // normally one2many so should be reversed...
    relationMap("targetElement") -> relationMap("sourceElement")
  })

  // ==== Items
  val optionalItemtype = items.itemtypes
  // println("optionalItemtype=" + optionalItemtype)
  // Some(ItemtypesType(List(ItemtypeType(List(DataRecord(),DataRecord(attributes,AttributeType(),...

  //Seq[(String, String, Seq[Seq[(String, String, Boolean)]])]
  val models = optionalItemtype.get.itemtype map (itemtypeType => {
    val typeCode = itemtypeType.code
    println("typeCode=" + typeCode)

    val extendsValue = itemtypeType.extendsValue match {
      case Some(extendsV) =>
        if (extendsV.trim.isEmpty) None else Some(extendsV)
      case None => None
    }
    println("extendsValue=" + extendsValue)

    //Seq[Option[Seq[(String, String, Boolean)]]]
    val optionalAttributes = itemtypeType.mixed map (dataRecord => {
      dataRecord.key match {
        case Some("attributes") => {
          val attributesType = dataRecord.value.asInstanceOf[AttributesType]
          val attributes = attributesType.attribute
          val optionalAttirubtes = attributes map (attribute => {
            // println("attribute.qualifier=" + attribute.qualifier)
            // val unique = attribute.modifiers.getOrElse(false).unique.getOrElse(false)

            println("attribute.modifiers=" + attribute.modifiers)
            val uniqueLabel = attribute.modifiers match {
              case Some(ModifiersType(_, _, _, _, _, _, _, _, Some(true), _, _)) => Option("unique")
              case Some(ModifiersType(_, _, _, _, _, _, _, _, Some(false), _, _)) => None
              case Some(ModifiersType(_, _, _, _, _, _, _, _, None, _, _)) => None
              case None => None
            }
            val optionalLabel = attribute.modifiers match {
              case Some(ModifiersType(_, _, _, Some(false), _, _, _, _, _, _, _)) => Option("required")
              case Some(ModifiersType(_, _, _, Some(true), _, _, _, _, _, _, _)) => None
              // Default is 'true' for optional.
              case Some(ModifiersType(_, _, _, None, _, _, _, _, _, _, _)) => None
              case None => None
            }
            val labels = Seq(uniqueLabel, optionalLabel)

            (attribute.qualifier, attribute.typeValue, labels)
          })
          Option(optionalAttirubtes)
        }
        case Some(something) => {
          println("something=" + something)
          None
        }
        case None => {
          println("None...")
          None
        }
      }
    })
    //    val attributes = optionalAttributes.filterNot(_ == None)(0).map(opt => { opt.get })
    //val attributes = optionalAttributes.filterNot(_ == None)(0).get
    //val attributes = optionalAttributes.filterNot(_ == None).map(opt => { opt.get })
    val attributes = optionalAttributes.collectFirst { case Some(v) => v }.getOrElse(Seq.empty[(String, String, Seq[Option[String]])])
    println("attributes=" + attributes)
    (typeCode, extendsValue, attributes)
  })

  // Ignored itemtype in typegroup, e.g. OrganizationOrderStatistics

  // ======== convert to GraphViz dot

  val relationGraphVizString = relationMaps.map(relation => { relation._1._1 + " -> " + relation._2._1 + " [style=dotted taillabel=" + relation._2._2 + " headlabel=" + relation._1._2 + "]" }).mkString("\n")

  // merge the same models
  // group by code
  val mergedModels = models.groupBy(_._1).map {
    case (code, duplicatableModel) => {
      if (duplicatableModel.size == 1) duplicatableModel(0)
      else {
        val attributes = duplicatableModel.map(_._3).flatten
        (code, duplicatableModel(0)._2, attributes)
      }
    }
  }

  val modelGraphVizString = mergedModels.map(model => {
    val attributeString = model._3.map(attribute => {

      val modifiersLabel = if (attribute._3.exists(_.isDefined)) {
        "[" + attribute._3.collect { case Some(modifier) => modifier }.mkString(", ") + "]"
      } else ""

      val typeName = attribute._2.split('.').last
      "- " + attribute._1 + " : " + typeName + " " + modifiersLabel
    }).mkString("\\l") + "\\l"

    val extendsLine = model._2 match {
      case Some(extendsV) =>
        "\n" + model._1 + " -> " + extendsV + " [label=\"extends\"]"
      case None => ""
    }

    model._1 + " [label=\"{" + model._1 + "|" + attributeString + "}\"]" +
      extendsLine
  })

  println("// " + "=" * 50)
  println("// relationGraphVizString")
  println(relationGraphVizString)
  println("// " + "=" * 50)
  println("// modelGraphVizString")
  println(modelGraphVizString.mkString("\n"))
}
