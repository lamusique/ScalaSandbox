package com.nekopiano.scala.shapeless.sandbox.guide.ch02


// Chapter 2
// Algebraic data types and generic representations

object Chapter2 extends App {

  import com.nekopiano.scala.utilities.CodeUtility._

  println(inspectFull("here"))

  // ================================================
  // with vanilla Scala

  // ADT, algebraic data types
  sealed trait Shape
  final case class Rectangle(width: Double, height: Double) extends Shape
  final case class Circle(radius: Double) extends Shape

  val rect: Shape = Rectangle(3.0, 4.0)
  val circ: Shape = Circle(1.0)


  def area(shape: Shape): Double =
    shape match {
      case Rectangle(w, h) => w * h
      case Circle(r) => math.Pi * r * r
    }

  val res1 = area(rect)
  println(inspect(res1))
  // res1: Double = 12.0

  val res2 = area(circ)
  println(inspect(res2))
  // res2: Double = 3.141592653589793

  // ================================================

  type Rectangle2 = (Double, Double)
  type Circle2 = Double
  type Shape2 = Either[Rectangle2, Circle2]
  val rect2: Shape2 = Left((3.0, 4.0))
  val circ2: Shape2 = Right(1.0)

  def area2(shape: Shape2): Double =
    shape match {
      case Left((w, h)) => w * h
      case Right(r) => math.Pi * r * r
    }

  val res4 = area2(rect2)
  println(inspect(res4))
  // res4: Double = 12.0
  val res5 = area2(circ2)
  println(inspect(res5))
  // res5: Double = 3.141592653589793


  // ================================================
  // 2.2 Generic product encodings
  // ================================================
  // with Shapeless

  import shapeless.{HList, ::, HNil}

  val product: String :: Int :: Boolean :: HNil =
    "Sunday" :: 1 :: false :: HNil

  val first = product.head
  println(inspect(first))
  // first: String = Sunday
  val second = product.tail.head
  println(inspect(second))
  // second: Int = 1
  val rest = product.tail.tail
  println(inspect(rest))
  // rest: shapeless.::[Boolean,shapeless.HNil] = false :: HNil

  println(inspect(product.toSized))


  //product.tail.tail.tail.head
  //// <console>:15: error: could not find implicit value for parameter c:
  //shapeless.ops.hlist.IsHCons[shapeless.HNil]
  ////        product.tail.tail.tail.head
  ////                               ^

  val newProduct: Long :: String :: Int :: Boolean :: HNil =
    42L :: product


  // ================================================
  // 2.2.1 Switching representations using Generic

  import shapeless.Generic

  case class IceCream(name: String, numCherries: Int, inCone: Boolean)

  val iceCreamGen = Generic[IceCream]
  println(inspect(iceCreamGen))
  // iceCreamGen: shapeless.Generic[IceCream]{type Repr = shapeless.::[String,shapeless.::[Int,shapeless.::[Boolean,shapeless.HNil]]]} = anon$macro$4$1@745fe7b1

  val iceCream = IceCream("Sundae", 1, false)
  println(inspect(iceCream))
  // iceCream: IceCream = IceCream(Sundae,1,false)

  val repr = iceCreamGen.to(iceCream)
  println(inspect(repr))
  // repr: iceCreamGen.Repr = Sundae :: 1 :: false :: HNil

  // From a gen rep back to an ADT, a case class
  val iceCream2 = iceCreamGen.from(repr)
  println(inspect(iceCream2))

  // iceCream2: IceCream = IceCream(Sundae,1,false)

  case class Employee(name: String, number: Int, manager: Boolean)

  // Create an employee from an ice cream:
  val employee = Generic[Employee].from(Generic[IceCream].to(iceCream))
  // a black magic
  // a ADT via a gen rep to another ADT
  println(inspect(employee))
  // employee: Employee = Employee(Sundae,1,false)


  // in callouts

  // a tuple
  val tupleGen = Generic[(String, Int, Boolean)]
  val genrepFromTuple = tupleGen.to(("Hello", 123, true))
  println(inspect(genrepFromTuple))
  // res4: tupleGen.Repr = Hello :: 123 :: true :: HNil
  val tupleFromGenrep = tupleGen.from("Hello" :: 123 :: true :: HNil)
  println(inspect(tupleFromGenrep))

  // res5: (String, Int, Boolean) = (Hello,123,true)


  // more than 22 fields
  case class BigData(
                      a: Int, b: Int, c: Int, d: Int, e: Int, f: Int, g: Int, h: Int, i: Int, j: Int,
                      k: Int, l: Int, m: Int, n: Int, o: Int, p: Int, q: Int, r: Int, s: Int, t: Int,
                      u: Int, v: Int, w: Int)

  val adt23 = Generic[BigData].from(Generic[BigData].to(BigData(
    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23)))
  println(inspect(adt23))
  // res6: BigData = BigData(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23)

  // currently 17 / 115 pages = 0.147826087 ≈ 15 %

  // ================================================
  // 2.3 Generic coproducts

  import shapeless.{Coproduct, :+:, CNil, Inl, Inr}
  case class Red()
  case class Amber()
  case class Green()
  type Light = Red :+: Amber :+: Green :+: CNil

  val red: Light = Inl(Red())
  println(inspect(red))
  // red: Light = Inl(Red())
  val green: Light = Inr(Inr(Inl(Green())))
  println(inspect(green))
  // green: Light = Inr(Inr(Inl(Green())))
  // L ➡︎ R ➡︎ R

  import shapeless.Generic
  sealed trait ShapeB
  final case class RectangleB(width: Double, height: Double) extends ShapeB
  final case class CircleB(radius: Double) extends ShapeB
  val gen = Generic[ShapeB]
  println(inspect(gen))
  // gen: shapeless.Generic[Shape]{type Repr = shapeless.:+:[Rectangle,shapeless.:+:[Circle,shapeless.CNil]]} = anon$macro$1$1@20ec902e

  val coprodRect = gen.to(RectangleB(3.0, 4.0))
  println(inspect(coprodRect))
  // ❌
  // res3: gen.Repr = Inl(Rectangle(3.0,4.0))
  // ✅
  // Inr(Inl(Rectangle(3.0,4.0)))
  // L ➡︎ R

  // a compilation error
  // println(inspect(gen.from(Inr(Inl(Rectangle(3.0,4.0))))))

  val genrepRectRed: CircleB :+: RectangleB :+: CNil = Inr(Inl(RectangleB(3.0, 4.0)))
  // Caveat
  // IntelliJ complains type mismatch but these below can be run.
  println(inspect(gen.from(genrepRectRed)))
  println(inspect(gen.from(Inr(Inl(RectangleB(3.0, 4.0))))))
  // Error:(195, 43) type mismatch;
  // found   : com.nekopiano.scala.shapeless.sandbox.guide.pt01.ch02.Chapter2.CircleB
  // required: com.nekopiano.scala.shapeless.sandbox.guide.pt01.ch02.Chapter2.RectangleB
  //  println(inspect(gen.from(Inr(Inl(CircleB(5.0))))))

  val coprodCirc = gen.to(CircleB(1.0))
  println(inspect(coprodCirc))
  // ❌
  // res4: gen.Repr = Inr(Inl(Circle(1.0)))
  // ✅
  // Inl(Circle(1.0))
  // L

//  val genrepCirc = Inl(Circle(1.0))
//  println(inspect(gen.from(genrepCirc)))


}
