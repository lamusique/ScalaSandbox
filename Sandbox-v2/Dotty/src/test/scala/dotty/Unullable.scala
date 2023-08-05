package dotty

object Unullable extends App {

  val someString = ""
  println(someString.getClass)

  val nullableString: String = null
  println(nullableString)

  val checkedNullableString: String | null = null
  println(checkedNullableString.getClass)

}
