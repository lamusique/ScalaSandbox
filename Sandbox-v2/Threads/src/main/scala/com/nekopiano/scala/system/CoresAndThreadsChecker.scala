package com.nekopiano.scala.system

import java.util.concurrent.atomic.AtomicInteger

import scala.collection.parallel._
import scala.sys.process._
import sourcecode.Text

import scala.collection.parallel.ForkJoinTaskSupport

object CoresAndThreadsChecker extends App {

  def scan[T](text: Text[T]) = text.source + "=" + text.value

  val logicalCoresOnJava = Runtime.getRuntime.availableProcessors
  println(scan(logicalCoresOnJava))

//  val ncpu = "sysctl -n hw.ncpu"
//  val ncpuStdo = ncpu.!!
//  println(scan(ncpuStdo))

//  hw.physicalcpu - The number of physical processors available in the current power management mode.
//  hw.physicalcpu_max - The maximum number of physical processors that could be available this boot.
//  hw.logicalcpu - The number of logical processors available in the current power management mode.
//  hw.logicalcpu_max - The maximum number of logical processors that could be available this boot.
  val cpuParams = List("hw.ncpu", "hw.physicalcpu", "hw.physicalcpu_max", "hw.logicalcpu", "hw.logicalcpu_max")
  cpuParams foreach (param => {
    val cmd = "sysctl -n " + param
    val stdo = cmd.!!
    print(cmd + ": " + stdo)
  })



  val parSeq = (1 to 20000).par
  //parSeq.tasksupport = new ForkJoinTaskSupport(new scala.concurrent.forkjoin.ForkJoinPool(4 * 8))
  parSeq.tasksupport = new ForkJoinTaskSupport(new scala.concurrent.forkjoin.ForkJoinPool(4000))

  val r = parSeq map {num => {
      val next = num + 1
      //println(Thread.currentThread() + ": " + next)
      (Thread.currentThread().getName, next)
    }
  }

  println(scan(r))

  val usedThreads = r.map(_._1).distinct.toList.sorted
  println(scan(usedThreads.size))
  println(scan(usedThreads))

}
