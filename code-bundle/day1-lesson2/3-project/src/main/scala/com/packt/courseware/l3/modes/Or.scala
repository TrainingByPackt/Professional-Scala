package com.packt.courseware.l3.modes

import com.packt.courseware.l3._

case class Or(frs: ChatbotMode, snd: ChatbotMode) extends ChatbotMode
{
  override def process(message: String, effects: EffectsProvider): LineStepResult =
  {
     frs.process(message, effects) match {
       case processed@Processed(answer,nextMode,endOfDialog) =>
         processed.copy(nextMode = Or(nextMode,snd))
       case Failed => snd.process(message,effects) match {
         case processed@Processed(answer,nextMode,endOfDialog) =>
           processed.copy(nextMode=Or(nextMode,frs))
         case Failed => Failed
       }
     }
  }

}
