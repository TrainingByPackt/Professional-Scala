package com.example

import doobie._
import doobie.implicits._

case class User(userId: Int, username: String, age: Int)

object User {

  def all(limit: Int): ConnectionIO[List[User]] = sql"""
    SELECT userId, username, age
    FROM user
    LIMIT $limit
  """.query[User].list

  def allManual(limit: Int): ConnectionIO[List[User]] = sql"""
    SELECT userId, username, age
    FROM user
    LIMIT $limit
  """
    .query[(Int, String, Int)]
    .map { case (id, username, age) => User(id, username, age) }
    .list

  def withUsername(username: String): ConnectionIO[User] = sql"""
    SELECT userId, username, age
    FROM user
    WHERE username = $username
  """.query[User].unique

  def create(username: String, age: Int): ConnectionIO[Int] = sql"""
    INSERT INTO user (username, age)
    VALUES ($username, $age)
  """.update.withUniqueGeneratedKeys[Int]("userId")

  def delete(userId: Int): ConnectionIO[Int] = sql"""
    DELETE FROM user
    WHERE userId = $userId
  """.update.run

  def setAge(userId: Int, age: Int): ConnectionIO[Int] = sql"""
    UPDATE user
    SET age = $age
    WHERE userId = $userId
  """.update.run

  val table: Update0 = sql"""
    CREATE TABLE user (
      userId INT AUTO_INCREMENT PRIMARY KEY,
      username VARCHAR(255) NOT NULL,
      age INT NOT NULL
    );
  """.update

}
