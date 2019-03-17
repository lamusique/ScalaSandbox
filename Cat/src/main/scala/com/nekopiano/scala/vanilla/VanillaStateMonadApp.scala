package com.nekopiano.scala.vanilla

object VanillaStateMonadApp extends App {

  // http://kmizu.hatenablog.com/entry/20091030/1256836322

  // ---- definitions
  class State[S, A](x: S => (A, S)) {
    val runState = x
    def flatMap[B](f: A => State[S, B]): State[S, B] = {
      new State(s => {
        val (v, s_) = x(s); f(v).runState(s_)
      })
    }
    def map[B](f: A => B): State[S, B] = {
      new State(s => {
        val (v, s_) = x(s); State.returns[S, B](f(v)).runState(s_)
      })
    }
  }

  object State {
    def returns[S, A](a: A): State[S, A] = new State(s => (a, s))
  }

  object MonadState {
    def get[S]: State[S, S] = new State(s => (s, s))
    def put[S](s: S): State[S, Unit] = new State(_ => ((), s))
  }


  // ---- usage
  class Var[T](c: T) {
    def :=(newContent: T): Var[T] = new Var(newContent)
    def unary_! : T = c
    override def toString(): String = "State(" + c.toString + ")"
  }

  def current: State[Var[Int], Int] =
    for (g <- MonadState.get[Var[Int]]) yield !g

  def add(n: Int): State[Var[Int], Int] =
    for (g <- MonadState.get[Var[Int]];
         x = !g + n;
         g_ = g := x;
         _ <- MonadState.put[Var[Int]](g_)
    ) yield x

  val foo = for (_ <- add(1);
                 _ <- add(2);
                 _ <- add(3);
                 r <- current
  ) yield r
  println(foo.runState(new Var(0))._1)
  // 6

}
