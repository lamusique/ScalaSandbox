package com.nekopiano.scala.janusgraph

import org.apache.tinkerpop.gremlin.structure.{Edge, Vertex}
import org.janusgraph.core.JanusGraphFactory

object JanusGraphScalaExample {

  def main(args: Array[String]): Unit = {

    // Create a graph.
    val graph = JanusGraphFactory.open("inmemory")
    val g = graph.traversal

    // Create a graph schema.
    {
      val mgmt = graph.openManagement
      val person = mgmt.makeVertexLabel("person").make
      val name = mgmt.makePropertyKey("name").dataType(classOf[String]).make
      val nameIndex = mgmt.buildIndex("nameIndex", classOf[Vertex]).addKey(name).buildCompositeIndex
      val age = mgmt.makePropertyKey("age").dataType(classOf[Integer]).make
      val ageIndex = mgmt.buildIndex("ageIndex", classOf[Vertex]).addKey(age).buildCompositeIndex
      val weight = mgmt.makePropertyKey("weight").dataType(classOf[java.lang.Double]).make
      val weightIndex = mgmt.buildIndex("weightIndex", classOf[Vertex]).addKey(weight).buildCompositeIndex
      val defeated = mgmt.makeEdgeLabel("defeated").make
      val year = mgmt.makePropertyKey("year").dataType(classOf[Integer]).make
      val yearIndex = mgmt.buildIndex("yearIndex", classOf[Edge]).addKey(year).buildCompositeIndex
      val score = mgmt.makePropertyKey("score").dataType(classOf[java.lang.Double]).make
      val scoreIndex = mgmt.buildIndex("scoreIndex", classOf[Edge]).addKey(score).buildCompositeIndex
      mgmt.commit
    }

    // Verify index statuses created.
    {
      val mgmt = graph.openManagement
      // Each should return ENABLED.
      println(mgmt.getGraphIndex("nameIndex").getIndexStatus(mgmt.getPropertyKey("name")))
      println(mgmt.getGraphIndex("ageIndex").getIndexStatus(mgmt.getPropertyKey("age")))
      println(mgmt.getGraphIndex("weightIndex").getIndexStatus(mgmt.getPropertyKey("weight")))
      println(mgmt.getGraphIndex("yearIndex").getIndexStatus(mgmt.getPropertyKey("year")))
      println(mgmt.getGraphIndex("scoreIndex").getIndexStatus(mgmt.getPropertyKey("score")))
      mgmt.rollback
    }

    // Add new vertices and edges
    val tyson = g.addV("person").property("name", "ironmike").property("age", 51).property("weight", 240.5d).next()
    val holyfield = g.addV("person").property("name", "realdeal").property("age", 55).property("weight", 226.0d).next()
    g.V(tyson).as("m").V(holyfield).addE("defeated").property("year", 1996).property("score", 8.5d).to("m").iterate
    g.V(tyson).as("m").V(holyfield).addE("defeated").property("year", 1997).property("score", 9.7d).to("m").iterate

    // a vertex by string index
    println(g.V().has("name", "ironmike").valueMap(true).toList)
    // a vertex by integer indexe
    println(g.V().has("age", 55).valueMap(true).toList)
    // a vertex by double index
    println(g.V().has("weight", 240.5d).valueMap(true).toList)
    // an edge by integer index
    println(g.E().has("year", 1996).valueMap(true).toList)
    // an edge by double index
    println(g.E().has("score", 9.7d).valueMap(true).toList)

    // Close the graph.
    graph.close
  }
}
