package com.nekopiano.scala.shapeless.sandbox.guide.ch03

// Chapter 2
// Algebraic data types and generic representations

object Chapter3 extends App {

  import com.nekopiano.scala.utilities.CodeUtility._

  println(inspectFull("here"))

  // ================================================

  // Turn a value of type A into a row of cells in a CSV file:
  trait CsvEncoder[A] {
    def encode(value: A): List[String]
  }

  // Custom data type:
  case class Employee(name: String, number: Int, manager: Boolean)
  // CsvEncoder instance for the custom data type:
  implicit val employeeEncoder: CsvEncoder[Employee] =
    new CsvEncoder[Employee] {
      def encode(e: Employee): List[String] =
        List(
          e.name,
          e.number.toString,
          if(e.manager) "yes" else "no"
        ) }

  def writeCsv[A](values: List[A])(implicit enc: CsvEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

  val employees: List[Employee] = List(
    Employee("Bill", 1, true),
    Employee("Peter", 2, false),
    Employee("Milton", 3, false)
  )

  val csvStrEmployees = writeCsv(employees)
  // res4: String =
  // Bill,1,yes
  // Peter,2,no
  // Milton,3,no

  println(inspect(csvStrEmployees))


  case class IceCream(name: String, numCherries: Int, inCone: Boolean)
  implicit val iceCreamEncoder: CsvEncoder[IceCream] =
    new CsvEncoder[IceCream] {
      def encode(i: IceCream): List[String] =
        List(
          i.name,
          i.numCherries.toString,
          if(i.inCone) "yes" else "no"
        ) }
  val iceCreams: List[IceCream] = List(
    IceCream("Sundae", 1, false),
    IceCream("Cornetto", 0, true),
    IceCream("Banana Split", 0, false)
  )
  val csvStrIceCreams = writeCsv(iceCreams)
  // res7: String =
  // Sundae,1,no
  // Cornetto,0,yes
  // Banana Split,0,no

  println(inspect(csvStrIceCreams))

  implicit def pairEncoder[A, B](
                                  implicit
                                  aEncoder: CsvEncoder[A],
                                  bEncoder: CsvEncoder[B]
                                ): CsvEncoder[(A, B)] =
    new CsvEncoder[(A, B)] {
      def encode(pair: (A, B)): List[String] = {
        val (a, b) = pair
        aEncoder.encode(a) ++ bEncoder.encode(b)
      } }

  val csvStrZipped = writeCsv(employees zip iceCreams)
  // res8: String =
  // Bill,1,yes,Sundae,1,no
  // Peter,2,no,Cornetto,0,yes
  // Milton,3,no,Banana Split,0,no

  println(inspect(csvStrZipped))


  object CsvEncoder {
    // "Summoner" method
    def apply[A](implicit enc: CsvEncoder[A]): CsvEncoder[A] =
      enc

    // pure
    // "Constructor" method
    def instance[A](func: A => List[String]): CsvEncoder[A] =
      new CsvEncoder[A] {
        def encode(value: A): List[String] =
          func(value)
      }
    // Globally visible type class instances
  }

  CsvEncoder[IceCream]
  // res9: CsvEncoder[IceCream] = $anon$1@72cbaad9

  implicitly[CsvEncoder[IceCream]]
  // res10: CsvEncoder[IceCream] = $anon$1@72cbaad9

  import shapeless._
  the[CsvEncoder[IceCream]]
  // res11: CsvEncoder[IceCream] = $anon$1@72cbaad9


  implicit val booleanEncoder: CsvEncoder[Boolean] =
    new CsvEncoder[Boolean] {
      def encode(b: Boolean): List[String] =
        if(b) List("yes") else List("no")
    }

  implicit val booleanEncoderShorter: CsvEncoder[Boolean] =
    CsvEncoder.instance(b => if(b) List("yes") else List("no"))


  // ================================================
  // 3.2 Deriving instances for products

  def createEncoder[A](func: A => List[String]): CsvEncoder[A] =
    new CsvEncoder[A] {
      def encode(value: A): List[String] = func(value)
    }
  implicit val stringEncoder: CsvEncoder[String] =
    createEncoder(str => List(str))
  implicit val intEncoder: CsvEncoder[Int] =
    createEncoder(num => List(num.toString))
  implicit val booleanEncoderC: CsvEncoder[Boolean] =
    createEncoder(bool => List(if(bool) "yes" else "no"))


  import shapeless.{HList, ::, HNil}
  implicit val hnilEncoder: CsvEncoder[HNil] =
    createEncoder(hnil => Nil)
  implicit def hlistEncoder[H, T <: HList](
                                            implicit
                                            hEncoder: CsvEncoder[H],
                                            tEncoder: CsvEncoder[T]
                                          ): CsvEncoder[H :: T] =
    createEncoder {
      case h :: t =>
        hEncoder.encode(h) ++ tEncoder.encode(t)
    }

  val reprEncoder: CsvEncoder[String :: Int :: Boolean :: HNil] =
    implicitly
  reprEncoder.encode("abc" :: 123 :: true :: HNil)
  // res9: List[String] = List(abc, 123, yes)


  // 3.2.2 Instances for concrete products

  import shapeless.Generic
  implicit val iceCreamEncoderB: CsvEncoder[IceCream] = {
    val gen = Generic[IceCream]
    val enc = CsvEncoder[gen.Repr]
    createEncoder(iceCream => enc.encode(gen.to(iceCream)))
  }
  writeCsv(iceCreams)
  // res11: String =
  // Sundae,1,no
  // Cornetto,0,yes
  // Banana Split,0,no


//  implicit def genericEncoder[A](
//                                  implicit
//                                  gen: Generic[A],
//                                  enc: CsvEncoder[???]
//                                ): CsvEncoder[A] = createEncoder(a => enc.encode(gen.to(a)))

//  implicit def genericEncoder[A](
//                                  implicit
//                                  gen: Generic[A],
//                                  enc: CsvEncoder[gen.Repr]
//                                ): CsvEncoder[A] =
//    createEncoder(a => enc.encode(gen.to(a)))
//  // <console>:27: error: illegal dependent method type: parameter may only be referenced in a subsequent parameter section
//  //          gen: Generic[A],
//  //          ^


  implicit def genericEncoder[A, R](
                                     implicit
                                     gen: Generic[A] { type Repr = R },
                                     enc: CsvEncoder[R]
                                   ): CsvEncoder[A] =
    createEncoder(a => enc.encode(gen.to(a)))


  writeCsv(iceCreams)

  writeCsv(iceCreams)(
    genericEncoder(
      Generic[IceCream],
      hlistEncoder(stringEncoder,
        hlistEncoder(intEncoder,
          hlistEncoder(booleanEncoder, hnilEncoder)))))


  implicit def genericEncoder[A, R](
                                     implicit
                                     gen: Generic.Aux[A, R],
                                     env: CsvEncoder[R]
                                   ): CsvEncoder[A] =
    createEncoder(a => env.encode(gen.to(a)))




}
