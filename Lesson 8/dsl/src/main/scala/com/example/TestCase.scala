package com.example

case class TestCase(description: TestDescription, run: () => TestResult)

case class TestDescription(name: String, specification: String) {
  def in(body: => Unit): TestCase = TestCase(this, TestResult.wrap(body))
}
