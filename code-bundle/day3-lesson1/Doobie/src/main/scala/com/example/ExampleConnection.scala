package com.example

import doobie._
import doobie.implicits._
import cats.effect.IO

object ExampleConnection extends App {

  val transactor =
    Transactor.fromDriverManager[IO](
      "org.h2.Driver", "jdbc:h2:mem:test", "sa", ""
    )

  val program: ConnectionIO[Int] =
    sql"select 42".query[Int].unique

  val task: IO[Int] =
    program.transact(transactor)

  val result: Int =
    task.unsafeRunSync

  println(s"Got result ${result}")

}
