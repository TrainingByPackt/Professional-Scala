package com.example

import scala.util.Try

sealed trait TestResult
object TestResult {
  def wrap(body: => Unit): () => TestResult = () => Try(body).fold(
    (u) => TestFailure(u.getMessage),
    _ => TestSuccess
  )
}

case class TestFailure(message: String) extends TestResult
case object TestSuccess extends TestResult
