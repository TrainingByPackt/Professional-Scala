package com.packt.courseware.l4.modes
import com.packt.courseware.l4.Processed

sealed trait StoreRemindCommand

case class StoreCommand(name:String,value:String) extends StoreRemindCommand
case class RemindCommand(name:String) extends StoreRemindCommand

case object StoreRemindCommand
{

  val StorePattern = raw"store ([^\W]+) (.*)".r;
  val RemindPattern = raw"remind ([^\W]+)".r;

  val empty: ChatbotMode = process(RemindEmpty)

  def process(state:RemindedState): ChatbotMode =
  {
    case (StorePattern(n,v),effects) => Processed("ok",process(state.store(n,v)),false)
    case (RemindPattern(n),effects) if state.isDefinedAt(n) => Processed(state(n),process(state),false)
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



