/**
 *
 */
package com.nekopiano.scala.scalasandbox.console

/**
 * http://stackoverflow.com/questions/1001290/console-based-progress-in-java
 * @author La Musique
 */
object ProgressConsoleDemo {

  // This works on traditional Windows console rather than Eclipse.

  def main(args: Array[String]): Unit = {

    var seq = Seq(20, 30)

    seq.par foreach runProgress _

  }

  def runProgress(sleep: Int) {
    // for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
    var progressPercentage = 0.0
    while (progressPercentage < 1.0) {
      updateProgress(progressPercentage)
      //      Thread.sleep(20)
      Thread.sleep(sleep)
      progressPercentage += 0.01
    }
  }

  def updateProgress(progressPercentage: Double) {
    val width = 50 // progress bar width in chars

    print("\r")
    print(Thread.currentThread)
    print("[")
    //System.out.print("\u0008[")
    val progressValue = (progressPercentage * width).toInt
    var varI = 0;
    //for (; i <= (int)(progressPercentage*width); i++) {
    for (i <- 0 to progressValue) {
      System.out.print(".");
      varI += 1
    }
    //    for (; i < width; i++) {
    for (i <- varI until width) {
      System.out.print(" ");
    }
    System.out.print("]");
  }
}
