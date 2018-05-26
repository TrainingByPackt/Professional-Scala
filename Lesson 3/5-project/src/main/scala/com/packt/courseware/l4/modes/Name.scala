package com.packt.courseware.l4.modes
import com.packt.courseware.l4.{EffectsProvider, Processed}

case class Name(name:String) extends ChatbotMode {

  override def isDefinedAt(x: (String, EffectsProvider)): Boolean = {
    x._1 == name
  }

  override def apply(v: (String, EffectsProvider)): Processed =
    v._1 match {
      case "my name" => Processed(name,this,false)
    }

}
