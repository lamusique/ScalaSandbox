/**
 *
 */
package com.nekopiano.scala.scalasandbox.image

import javax.imageio.ImageIO
import java.net.URL
import java.io.File
import java.awt.RenderingHints
import java.awt.Color

/**
 * @author La Musique
 *
 */
object PutTextOnImage extends App {

  util.Properties.setProp("scala.time", "")

  //in parallel
  (1 to 2000).par foreach { i =>

    // http://stackoverflow.com/questions/10929524/how-to-add-text-to-an-image-in-java

    //      final BufferedImage image = ImageIO.read(new URL(
    //        "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));
    val image = ImageIO.read(new File(
      // raw"C:\Users\La Musique\Pictures\UploadSamples\Generator\img_bt1s1620_01-500Wx500H.jpg"));
      raw"C:\Users\La Musique\Pictures\UploadSamples\Generator\img_bt1s1620_01-300Wx300H.jpg"));

    // http://stackoverflow.com/questions/7550454/how-can-i-make-the-text-edges-in-the-images-smooth
    //  val g = image.getGraphics()
    val g = image.createGraphics

    g.setRenderingHint(
      RenderingHints.KEY_ALa MusiqueIALIASING,
      RenderingHints.VALUE_ALa MusiqueIALIAS_ON)
    //    g.setFont(g.getFont().deriveFont(50f))
    g.setFont(g.getFont().deriveFont(30f))
    g.setColor(Color.DARK_GRAY)

    val seq = "%05d".format(i)
    //    g.drawString("Sample " + seq, 15, 190)
    g.drawString("Sample " + seq, 9, 117)
    g.dispose()

    ImageIO.write(image, "jpg", new File(raw"C:\Users\La Musique\Pictures\UploadSamples\Generator\generated-300\sample-bath-" + seq + ".jpg"));
    println(seq + " has been created.")
  }
  println("finished")
}
