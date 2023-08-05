package nekopiano.akkaquartzscheduler

import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension

object AkkaQuartzSchedulerTrial extends App {


  val system = ActorSystem("SampleSystem")
  val actor = system.actorOf(Props(classOf[SampleActor]))


  val scheduler = QuartzSchedulerExtension(system)

  QuartzSchedulerExtension(system).schedule("Every5Seconds", actor, "5 sec.")


  val tempSchedule = scheduler.createSchedule("temp", Option("a temporary schedule"), "*/2 * * ? * *")

  scheduler.schedule("temp", actor, "2 sec.")


}

class SampleActor extends Actor {

  def receive = {
    case msg: String => println(msg + new Date)
  }

}
