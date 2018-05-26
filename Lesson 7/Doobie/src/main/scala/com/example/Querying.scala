package co.example

import doobie._
import doobie.implicits._
import cats.effect.IO

object Querying extends App {

  val transactor =
    Transactor.fromDriverManager[IO](
      "org.h2.Driver", "jdbc:h2:mem:test", "", ""
    )

  val fragment: Fragment =
    sql"select 42"

  val query: Query0[Int] =
    fragment.query[Int]

  val program: ConnectionIO[Int] =
    query.unique

  val task: IO[Int] =
    program.transact(transactor)

  val result: Int =
    task.unsafeRunSync

  println(s"Got result ${result}")


}
