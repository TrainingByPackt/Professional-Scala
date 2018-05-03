package com.packt.courseware.l6.modes
import com.packt.courseware.l6.{EffectsProvider, Processed}

case class Name(name:String) extends ChatbotMode {

  override def process(line: String, effects: EffectsProvider): Option[Processed] =
    line match {
      case "my name" => Some(Processed(name,this,false))
      case _ => None
    }

}
