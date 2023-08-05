package nekopiano.quartz

import java.util.Date

import org.quartz.JobBuilder._
import org.quartz.SimpleScheduleBuilder._
import org.quartz.TriggerBuilder._
import org.quartz.impl.StdSchedulerFactory
import org.quartz.{Job, JobExecutionContext, JobExecutionException}

object QuartzTrial extends App {
// http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-01.html

  val schedFact = new StdSchedulerFactory

  val sched = schedFact.getScheduler

  sched.start()

  // define the job and tie it to our HelloJob class
  val job = newJob(classOf[MyJob]).withIdentity("myJob", "group1").build

  // Trigger the job to run now, and then every 2 seconds
  val trigger = newTrigger.withIdentity("myTrigger", "group1")
    //and five times it runs.
    //.startNow.withSchedule(simpleSchedule.withIntervalInSeconds(2).withRepeatCount(4)).build
    // never ends
    .startNow.withSchedule(simpleSchedule.withIntervalInSeconds(5).repeatForever()).build

  // Tell quartz to schedule the job using our trigger
  sched.scheduleJob(job, trigger)


  // non stop after booting Quartz
}


class MyJob extends Job {
  @throws[JobExecutionException]
  override def execute(jobExecutionContext: JobExecutionContext): Unit = {
    System.out.println("My Logic at " + new Date)
  }
}

