package com.example

import cats.effect.IO
import doobie._
import doobie.implicits._

object Main extends App {

  val xa = Transactor.fromDriverManager[IO](
    "org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "", ""
  )

  val program = for {
    _  <- Tables.create
    userId <- User.create("Jack", 41)
    _ <- User.setAge(userId, 42)
    _ <- Todo.create(userId, "Buy Milk", false)
    _ <- Todo.create(userId, "Read the newspaper", true)
    _ <- Todo.create(userId, "Read the full documentation for Doobie", false)
    uncompleted <- Todo.uncompleted(userId)
  } yield uncompleted

  val all: IO[Unit] = for {
    todos <- program.transact(xa)
    users <- User.all(10).transact(xa)
  } yield {
    todos.foreach(println)
    users.foreach(println)
  }

  all.unsafeRunSync

}
