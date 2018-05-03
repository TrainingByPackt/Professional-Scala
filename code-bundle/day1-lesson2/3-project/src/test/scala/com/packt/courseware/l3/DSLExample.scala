package com.packt.courseware.l3

import scala.util.control.NonFatal

class DSLExample {

  var name: String = "undefined"
  var code: ()=>Unit = { () => () }

  def test(testName:String)(testCode: =>Unit):Unit = {
    name = testName
    code = () => testCode
  }

  def run(): Boolean = {
    try {
      code()
      true
    } catch {
      case ex: Exception =>
        ex.printStackTrace()
        false
    }
  }

}
