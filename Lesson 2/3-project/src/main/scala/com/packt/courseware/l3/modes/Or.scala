package com.packt.courseware.l3.modes

import com.packt.courseware.l3._

case class Or(frs: ChatbotMode, snd: ChatbotMode) extends ChatbotMode
{
  override def process(message: String, effects: EffectsProvider): LineStepResult =
  {
     frs.process(message, effects) match {
       case Processed(answer,nextMode,endOfDialog) => Processed(answer, Or(nextMode,snd),endOfDialog)
       case Failed => snd.process(message,effects) match {
         case Processed(answer,nextMode,endOfDialog) => Processed(answer, Or(nextMode,frs),endOfDialog)
         case Failed => Failed
       }
     }
  }

}
