/**
 *
 */
package com.nekopiano.scala.scalasandbox.concurrent

/**
 * @author La Musique
 *
 */
object ParTest extends App {

  val seq = 1 to 50

  notSync()
  sync()

  def notSync() {
    println("=" * 15)
    println("availableProcessors=" + scala.collection.parallel.availableProcessors)
    val order = seq.par.map(num => {
      (System.nanoTime, num, Thread.currentThread)
    })

    order.seq sortBy (_._1) foreach println _
    // disordered...

  }

  def sync() {
    println("=" * 15)
    println("availableProcessors=" + scala.collection.parallel.availableProcessors)
    val syncedOrder = seq.par.map(num => {
      (syncNano(), num, Thread.currentThread)
    })

    syncedOrder.seq sortBy (_._1) foreach println _
    // disordered...

  }
  def syncNano(): Long = synchronized {
    count += 1
    count
  }
  var count = 0L

}

