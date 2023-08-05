package com.nekopiano.scala.di.compiletime.readermonad

import cats._, cats.data._, cats.implicits._

object ReaderMonadDiApp extends App {

  val f = (_: Int) * 2
  val g = (_: Int) + 10
  println((g map f)(8))

  val addStuff: Int => Int = for {
    a <- (_: Int) * 2
    b <- (_: Int) + 10
  } yield a + b
  println(addStuff(3))



  case class User(id: Long, parentId: Long, name: String, email: String)
  trait UserRepo {
    def get(id: Long): User
    def find(name: String): User
  }

  trait Users {
    def getUser(id: Long): UserRepo => User = {
      case repo => repo.get(id)
    }
    def findUser(name: String): UserRepo => User = {
      case repo => repo.find(name)
    }
  }

//  trait Users {
//    def getUser(id: Long) = Reader((repo: UserRepo) =>
//      repo.get(id)
//    )
//    def findUser(name: String) = Reader((repo: UserRepo) =>
//      repo.find(name)
//    )
//  }

  val testUsers = List(User(0, 0, "Vito", "vito@example.com"),
    User(1, 0, "Michael", "michael@example.com"),
    User(2, 0, "Fredo", "fredo@example.com"))

  object UserInfo extends Users {
    def userInfo(name: String): UserRepo => Map[String, String] =
      for {
        user <- findUser(name)
        boss <- getUser(user.parentId)
      } yield Map(
        "name" -> s"${user.name}",
        "email" -> s"${user.email}",
        "boss_name" -> s"${boss.name}"
      )
  }

//  object UserInfo extends Users {
//    def userInfo(name: String) = Reader {
//      (repo: UserRepo) =>
//      for {
//        user <- findUser(name)
//        boss <- getUser(user.parentId)
//      //} yield user.name
//      } yield Map(
//        "name" -> s"${user.name}",
//        "email" -> s"${user.email}",
//        "boss_name" -> s"${boss.name}"
//        )
//    }
//  }

  trait Program {
    def app: UserRepo => String =
      for {
        fredo <- UserInfo.userInfo("Fredo")
      } yield fredo.toString
  }

//  trait Program {
//    def app = Reader((repo: UserRepo) =>
//        for {
//          fredo <- UserInfo.userInfo("Fredo")
//        } yield fredo
//    )
//  }

  object Main extends Program {
    def run: String = app(mkUserRepo)
    def mkUserRepo: UserRepo = new UserRepo {
      def get(id: Long): User = (testUsers find { _.id === id }).get
      def find(name: String): User = (testUsers find { _.name === name }).get
    }
  }

//  object Main extends Program {
//    def run = app.run(mkUserRepo).run(mkUserRepo).run(mkUserRepo)
//
//    def mkUserRepo: UserRepo = new UserRepo {
//      def get(id: Long): User = (testUsers find {
//        _.id === id
//      }).get
//
//      def find(name: String): User = (testUsers find {
//        _.name === name
//      }).get
//    }
//  }

  println("starts.")
  val ret = Main.run
  println(ret)
  println("ends.")
}
