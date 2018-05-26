package com.packt.courseware.l4

import org.scalatest.FunSuite


trait Drink
{
   def baseSubstation: String
   def flavour: String
   def description: String
}


trait VanillaFlavour
{
   thisFlavour: Drink =>

   def flavour = "vanilla"

   override def description: String = s"Vanilla ${baseSubstation}"
}

trait SpecieFlavour
{
  thisFlavour: Drink =>

  override def description: String = s"${baseSubstation} with ${flavour}"

}

trait Tee
{
  thisTee: Drink =>

  override def baseSubstation: String = "tee"

}



trait Operation
{

  def doOperation(): Unit

}

trait PrintOperation
{
  this: Operation =>

  def doOperation():Unit = Console.println("A")
}

trait LoggedOperation extends Operation
{
  this: Operation =>

  abstract override def doOperation():Unit = {
    Console.print("start")
    super.doOperation()
    Console.print("end")
  }

}



class SelfTypeAnnotationTest extends FunSuite {

}
