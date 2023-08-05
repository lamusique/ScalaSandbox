package com.nekopiano.scala.vanilla

import utils.CodeUtility._

object VanillaStateMonadApp extends App {

  // http://kmizu.hatenablog.com/entry/20091030/1256836322

  // ---- definitions
  class State[S, A](x: S => (A, S)) {
    val runState = x
    def flatMap[B](f: A => State[S, B]): State[S, B] = {
      println(inspect(f))
//      new State(s => {
//        val (v, s_) = x(s); f(v).runState(s_)
//      })
      val ns = new State[S, B](s => {
        println(inspect(s))
        println(inspect(x))
        val (v, s_) = x(s)
        println(inspect(v))
        println(inspect(s_))
        val runS = f(v).runState(s_)
        println(inspect(runS))
        runS
      })
      ns
    }

    def map[B](f: A => B): State[S, B] = {
      println(inspect(f))
      //      new State(s => {
      //        val (v, s_) = x(s); State.returns[S, B](f(v)).runState(s_)
      //      })
      val tstS =  new State[S, B](s => {
        val as = x(s)
        val bs = f(as._1)
        (bs, s)
      })
      println(inspect(tstS))

      val ns = new State[S, B](s => {
        println(inspect(s))
        println(inspect(x))
        val (v, s_) = x(s)
        println(inspect(v))
        println(inspect(s_))
        val r = State.returns[S, B](f(v))
        println(inspect(r))
        val runS = r.runState(s_)
        println(inspect(runS))
        runS
      })
      ns
    }
  }

  object State {
    def returns[S, A](a: A): State[S, A] = {
      println(inspect(a))
      new State(s => (a, s))
    }
  }

  object MonadState {
    def get[S]: State[S, S] = {
      println(inspect("get"))
      // new State(s => (s, s))

      // This indirect declaration can't be inferred.
      // val s = new State(s => (s, s))
      val s = new State[S, S](s => (s, s))
      s
    }

    def put[S](s: S): State[S, Unit] = {
      println(inspect(s))
      // new State(_ => ((), s))

      // val ns = new State(_ => ((), s))
      val ns = new State[S, Unit](_ => ((), s))
      ns
    }

  }


  // ---- usage
  class Var[T](c: T) {
    def :=(newContent: T): Var[T] = {
      println(inspect(newContent))
      new Var(newContent)
    }
    def unary_! : T = {
      println(inspect(c))
      c
    }
    //override def toString(): String = "State(" + c.toString + ")"
    override def toString(): String = "Var(" + c.toString + ")"
  }

  def current: State[Var[Int], Int] = {
    println(inspect("current"))
    //for (g <- MonadState.get[Var[Int]]) yield !g
    val s = for (
      g <- MonadState.get[Var[Int]]
    ) yield !g
    s
  }

  def add(n: Int): State[Var[Int], Int] = {
    println(inspect(n))
    for (g <- MonadState.get[Var[Int]];
         x = !g + n;
         g_ = g := x;
         _ <- MonadState.put[Var[Int]](g_)
    ) yield x
  }

  val foo = for (_ <- add(1);
                 _ <- add(2);
                 _ <- add(3);
                 r <- current
  ) yield r
  println(inspect(foo))

  val res = foo.runState(new Var(0))._1
  println(inspect(res))
  // 6


  def addLoop(n: Int): State[Var[Int], Int] = {
    val s = MonadState.get[Var[Int]].flatMap(g => {
      val x = !g + n
      val g_ = g := x
      MonadState.put[Var[Int]](g_).map(a => x)
    })
    s
  }
  def currentLoop: State[Var[Int], Int] = {
    MonadState.get[Var[Int]].map(g =>
      !g)
  }

  val fooLoop =
    addLoop(1).flatMap(a =>
      addLoop(2).flatMap(b =>
        addLoop(3).flatMap(c =>
          currentLoop.map(d =>
            d
          )
        )
      )
    )

  val resLoop = fooLoop.runState(new Var(0))._1
  println(inspect(resLoop))
  // 6


}
