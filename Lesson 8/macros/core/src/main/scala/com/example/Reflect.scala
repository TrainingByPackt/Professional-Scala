package com.example

import scala.reflect.runtime.{universe => ru}

object Reflect extends App {

  def getTypeTag[T: ru.TypeTag](obj: T) = ru.typeTag[T]

  getTypeTag(List(1,2,3)).tpe.decls
    .take(10)
    .filter(_.name.toString.length < 5)
    .foreach(d => println(s"${d.name} : ${d.typeSignature}"))

}
