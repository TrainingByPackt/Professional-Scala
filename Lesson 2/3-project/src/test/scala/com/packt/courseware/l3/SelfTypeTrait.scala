package com.packt.courseware.l3

object SelfTypeExample {


   trait Drink {
     def baseSubstance: String
     def flavour: String
   }

   trait VanillaFlavor
   {
     this: Drink =>

     def flavour = "vanilla"

   }

   trait GreenTee {
     this: Drink =>

     override def baseSubstance: String = "green tee"
   }

}
