package com.packt.courseware.l7

import org.scalatest.FunSuite


case class Person(firstName: String, lastName: String)


class RunLater(x: =>Unit)
{
  def run(): Unit = x
}


class FunctionCallSyntaxCallTest extends FunSuite {

  test("copy call") {
    val p1 = Person("Jon","Bull")
    val p2 = p1.copy(firstName = "Iyan")
    assert(p1.lastName == p2.lastName)
  }

  test("named syntax for any method") {
    val mode = Chatbot7.createInitMode()

  }

  test("call by value") {
    def f(x:Int) = x + x + 1

    f({ println("A ");  10 })  //  A  res: 21

  }

  test("later syntax") {

    var x = 0
    val later = new RunLater({ x=x+1 })
    assert(x==0)
    later.run()
    assert(x==1)

  }

  test("call by need") {

    def f(x: =>Int): Int = {
      lazy val nx = x
      nx + nx + 1
    }

    f({ println("FunctionCallSyntaxCallTest:A ");  10 })  //  A  res: 21

  }

  def until(condition: =>Boolean)(body: =>Unit) =
  {
    while(!condition) body
  }



}
