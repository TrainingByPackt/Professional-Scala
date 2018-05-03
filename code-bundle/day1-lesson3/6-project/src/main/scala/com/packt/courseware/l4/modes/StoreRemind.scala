package com.packt.courseware.l4.modes
import com.packt.courseware.l4.Processed
import com.packt.courseware.l4.modes.StoreRemindCommand.{RemindPattern, StorePattern}

sealed trait StoreRemindCommand

case class StoreCommand(name:String,value:String) extends StoreRemindCommand
case class RemindCommand(name:String) extends StoreRemindCommand

case object StoreRemindCommand extends
{

  val StorePattern = raw"store ([^\W]+) (.*)".r;
  val RemindPattern = raw"remind ([^\W]+)".r;

  val empty: ChatbotMode = processor(RemindEmpty)

  def processor(state:RemindedState): ChatbotMode = { (line,effects) =>
    line match {
      case StorePattern(n,v) => Some(Processed("ok",processor(state.store(n,v)),false))
      case RemindPattern(n) if state.isDefinedAt(n) => Some(Processed(state(n),processor(state),false))
      case _ => None
    }
  }

}

sealed trait RemindedState extends PartialFunction[String,String]
{

  def store(name:String,value:String): RemindedState =
        new RemindCons(name,value,this)

}

class RemindCons(name:String,value:String,next:RemindedState) extends RemindedState {

  override def isDefinedAt(x: String): Boolean = x == name || next.isDefinedAt(x)

  override def apply(x: String): String =
    if (x==name) value else next(x)

}


case object RemindEmpty extends RemindedState
{
  override def isDefinedAt(x: String): Boolean = false

  override def apply(x: String): String =
    throw new MatchError(x)

}



