// Topic B: Exploring Pattern Matching
// Subtopic: Minimal Example
// Exercise: Getting started with the minimal example.
// Step 4:

sealed abstract class Option[+A]  {
 
 def isEmpty: Boolean
 def isDefined: Boolean = !isEmpty
 def get: A

  // …  other methods

}

final case class Some[+A](value: A) extends Option[A] {
  def isEmpty = false
  def get = value
}

case object None extends Option[Nothing] {
  def isEmpty = true
  def get = throw new NoSuchElementException(“None.get”)
}
