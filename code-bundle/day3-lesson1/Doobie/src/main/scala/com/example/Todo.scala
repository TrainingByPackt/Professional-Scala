package com.example

import doobie._
import doobie.implicits._
import cats._, cats.data._, cats.effect.IO, cats.implicits._

case class Todo(
  userId: Int,
  title: String,
  completed: Boolean
)

object Todo {

  def create(userId: Int, title: String, completed: Boolean): ConnectionIO[Int] = sql"""
    INSERT INTO todo (userId, title, completed)
    VALUES ($userId, $title, $completed);
  """.update.run

  def uncompleted(userId: Int): ConnectionIO[List[Todo]] = sql"""
    SELECT userId, title, completed
    FROM todo
    WHERE
      completed = false AND
      userId = $userId
  """.query[Todo].list

  val table: Update0 = sql"""
    CREATE TABLE todo (
      todoId INT AUTO_INCREMENT PRIMARY KEY,
      userId INT NOT NULL,
      title VARCHAR(255) NOT NULL,
      completed BOOL DEFAULT false,
      FOREIGN KEY (userId) references user(userId)
    );
  """.update

}
