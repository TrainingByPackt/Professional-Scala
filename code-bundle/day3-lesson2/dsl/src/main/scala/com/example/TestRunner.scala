package com.example

object TestRunner {
  def run(tests: TestCase*): Unit = {
    val line = "-" repeat 80

    println(s"\n${line}")
    println(s"Running ${tests.size} tests:")
    println(s"${line}\n")

    var failures = 0
    var successes = 0

    tests.foreach { test =>
      println(s"  ${test.description.name}: ${test.description.specification}")
      test.run() match {
        case TestFailure(message) =>
          successes += 1
          println(s"    Failed: $message")
        case TestSuccess =>
          failures += 1
          println("    Success")
      }
    }

    println(s"\n${line}")
    println(s"Result: ${failures} failures and ${successes} successes.")
    println(s"${line}\n")
  }
}
