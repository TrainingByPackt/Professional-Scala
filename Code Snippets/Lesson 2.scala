// Topic A: Objects, Classes and Traits
// Subtopic: Self-Types

trait Drink
{
 def baseSubstation: String
 def flavour: String
 def description: String
}


trait VanillaFlavour
{
 thisFlavour: Drink =>

 def flavour = “vanilla”
 override def description: String = s”Vanilla ${baseSubstation}”
}

trait SpecieFlavour
{
 thisFlavour: Drink =>

 override def description: String = s”${baseSubstation} with ${flavour}”
}

trait Tee
{
  thisTee: Drink =>

  override def baseSubstation: String = “tee”

  override def description: String = “tee”


    def withSpecies: Boolean = (flavour != “vanilla”)




// Topic A: Objects, Classes and Traits
// Exercise: Exploring annotation
// Step 3:

trait Operation
{

  def doOperation(): Unit

}

trait PrintOperation
{
  this: Operation =>

  def doOperation():Unit = Console.println(“A”)
}

trait LoggedOperation extends Operation
{
  this: Operation =>

  abstract override def doOperation():Unit = {
    Console.print(“start”)
    super.doOperation()
    Console.print(“end”)
  }
}




// Topic B: OO in Our Chatbot
// Subtopic: Decoupling Logic and Environment
// # 1

trait EffectsProvider extends TimeProvider {

 def input: UserInput

 def output: UserOutput

}

object DefaultEffects extends EffectsProvider
{
 override def input: UserInput = ConsoleInput

 override def output: UserOutput = ConsoleOutput

 override def currentTime(): LocalTime = LocalTime.now()

 override def currentDate(): LocalDate = LocalDate.now()
}



// Topic B: OO in Our Chatbot
// Subtopic: Decoupling Logic and Environment
// # 2

trait UserOutput {

 def write(message: String): Unit

 def writeln(message: String): Unit = {
  write(message)
  write(“\n”)
 }

}


object ConsoleOutput extends UserOutput
{

 def write(message: String): Unit = {
  Console.print(message)
 }
}


// Topic B: OO in Our Chatbot
// Exercise: Moving the first interaction to mode.
// Step 2:

test("step of my-name") {
  val mode = Chatbot3.createInitMode()
  val effects = new EffectsProvider {
    override val output: UserOutput = (message: String) => {}

    override def input: UserInput = () => "Joe"

    override def currentDate(): LocalDate = Chatbot3.effects.currentDate()

    override def currentTime(): LocalTime = Chatbot3.effects.currentTime()
  }
  val result1 = mode.process("my name",effects)
  assert(result1.isInstanceOf[Processed])
  val r1 = result1.asInstanceOf[Processed]
  assert(r1.answer == "Hi, Joe")
  val result2 = r1.nextMode.process("my name",effects)
  assert(result2.isInstanceOf[Processed])
  val r2 = result2.asInstanceOf[Processed]
  assert(r2.answer == "Your name is Joe")

}

