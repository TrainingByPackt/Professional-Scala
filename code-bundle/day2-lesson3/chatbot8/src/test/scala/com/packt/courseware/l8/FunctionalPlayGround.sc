
val pf: PartialFunction[Int,String] = {
  case 0 => "zero"
  case 1 => "one"
  case 2 => "two"
  case x:Int if x>0 => "many"
}

pf(1)
pf.isDefinedAt(1)
pf.isDefinedAt(-1)


val pf1: PartialFunction[Int,String] = pf orElse { case _ => "other" }
pf1(-1)

pf andThen (_.length)

case class Wrapper(x:Int)

def doSomething(x:Int):Unit =
{
  println("doSomething $x")
}


val r1 = "([\\d]+)".r
val r2 = "([\\d]+)  ([^\\W]*)".r

"333" match {
  case r1(x) => "1"
  case r2(x,y) => "2"
}

