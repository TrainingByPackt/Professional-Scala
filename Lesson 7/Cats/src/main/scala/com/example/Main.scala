package com.example

object Main extends App {
  println(User.validate("!!", -1))
  println(User.validate("jack", 42))
}
