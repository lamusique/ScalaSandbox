package nekopiano


import java.util.Date

import org.quartz.Job
import org.quartz.JobBuilder._
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.TriggerBuilder._
import org.quartz.impl.StdSchedulerFactory
import org.quartz.SimpleScheduleBuilder._

object QuartzTrial extends App {
// http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-01.html

  import org.quartz.JobDetail
  import org.quartz.Scheduler
  import org.quartz.SchedulerFactory
  import org.quartz.Trigger

  val schedFact = new StdSchedulerFactory

  val sched = schedFact.getScheduler

  sched.start()

  // define the job and tie it to our HelloJob class
  val job = newJob(classOf[MyJob]).withIdentity("myJob", "group1").build

  // Trigger the job to run now, and then every 2 seconds, and five times it runs.
  val trigger = newTrigger.withIdentity("myTrigger", "group1")
    .startNow.withSchedule(simpleSchedule.withIntervalInSeconds(2).withRepeatCount(4)).build

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

