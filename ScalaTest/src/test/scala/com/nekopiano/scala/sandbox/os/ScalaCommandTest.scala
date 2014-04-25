/**
 *
 */
package com.nekopiano.scala.sandbox.os

import scala.sys.process._
import java.io.IOException

/**
 * @author La Musique
 *
 */
object CommandTest {

  def main(args: Array[String]): Unit = {

    println("started")
    //    val res1 = "java -version".!!
    //    println("res1" + res1)
    //    val res2 = "cmd /c dir".!!
    //    println("res2" + res2)
    //
    //    val s = raw"java -jar C:\Repos\scala\MorleySbt\target\scala-2.10\MorleySbt-assembly-1.0.jar -s".!!
    //    println("s=" + s)

    //val e = raw"java -jar C:\Repos\scala\MorleySbt\target\scala-2.10\MorleySbt-assembly-1.0.jar -e".!!

    val result = "cmd /c dir".!!
    println("result=" + result)

    val currentPathDos = "cmd /c chdir".!!
    println("currentPathDos=" + currentPathDos)
    val currentPathJava = System.getProperty("user.dir")
    println("currentPathJava=" + currentPathJava)

    println("finished")

  }

  //  def main(args: Array[String]): Unit = {
  //
  //    //val command = new ExecuteShellCommand()
  //
  //    val domainName = "google.com";
  //
  //    //in mac oxs
  //    //val command = "ping -c 3 " + domainName;
  //
  //    //in windows
  //    val command = "ping -n 3 " + domainName;
  //
  //    val output = executeCommand(command);
  //    System.out.println(output);
  //  }
  //  private def executeCommand(command: String) = {
  //
  //    val output = new StringBuffer();
  //
  //    val p = Runtime.getRuntime().exec(command)
  //    p.waitFor()
  //    val reader =
  //      new BufferedReader(new InputStreamReader(p.getInputStream()));
  //
  //    var line = "";
  //    while ((line = reader.readLine()) != null) {
  //      output.append(line + "\n");
  //    }
  //
  //    output.toString
  //  }
}
