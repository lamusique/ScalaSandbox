package com.nekopiano.scala.system

import java.util.concurrent.atomic.AtomicInteger

object MaxThreadsChecker extends App {


  val count = new AtomicInteger(0)

  while(true) {
    val thread = new Thread {
      override def run {
        println("A new thread starts. " + count.incrementAndGet())
        try{
          while(true) {
            Thread.sleep(1000)
          }
        } catch {
          case e: Exception =>
            System.err.println("e: " + e)
            throw e
        }
      }
    }
    thread.start()

  }


}
