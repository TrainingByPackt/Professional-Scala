package com

package object example {

  implicit class StringExtension(val name: String) extends AnyVal {
    def should(specification: String): TestDescription =
      TestDescription(name, specification)
  }

  implicit class StringExtensions(val self: String) extends AnyVal {
    def repeat(count: Int): String =
      List.range(0, count).map(_ => self).fold("")(_ + _)
  }

}
