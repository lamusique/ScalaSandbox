/**
 *
 */
package com.nekopiano.scala.sandbox.reflection

import scala.reflect.runtime.universe._
import scala.reflect._

/**
 * @author La Musique
 *
 */
object ReflectionSample {
  
  def a(){
        val ti = typeTag[Int]
  }

  def main(args: Array[String]) {

    val m = toMap(User("Kudo", 2, false))
    println(m)
    //Map(admin_?  -> false, gender  -> 2, name  -> Kudo)

    val u = new User()
    fromMap(u, Map("name" -> "Riki", "gender" -> "1", "admin_?" -> "true"))
    println(u)
    //User(Riki,1,true)

  }

  def toMap[T](v: T)(implicit tt: TypeTag[T], ct: ClassTag[T]) = {
    val t = typeOf[T]
    // var だけを抽出
    val vars = t.members.filter(_ match {
      case t: TermSymbol => t.isVar
      case _ => false
    })

    // Reflection実行のためのMirrorを取得
    val mirror = tt.mirror // runtimeMirror(getClass.getClassLoader)でもOK

    // インスタンス操作のためのReflectionを取得
    val rf = mirror.reflect(v)

    var fieldValues: Map[String, String] = Map.empty

    vars.foreach(v => v match {
      case v: TermSymbol => {
        // GetterMethodを取得
        val getterMethod = rf.reflectMethod(v.getter.asMethod)
        fieldValues += (v.name.decoded.trim -> getterMethod().toString) //nameを取得すると末尾にスペースが入るのでtrimしておく
      }
    })
    fieldValues
  }

  def fromMap[T](v: T, map: Map[String, String])(implicit tt: TypeTag[T], ct: ClassTag[T]) = {
    val t = typeOf[T]

    // var だけを抽出
    val vars = t.members.filter(_ match {
      case t: TermSymbol => t.isVar
      case _ => false
    })
    // Reflection実行のためのMirrorを取得
    val mirror = tt.mirror // runtimeMirror(getClass.getClassLoader)でもOK

    // インスタンス操作のためのReflectionを取得
    val rf = mirror.reflect(v)

    vars.foreach(v => v match {
      case t: TermSymbol => {
        map.get(t.name.decoded.trim) match {
          case Some(v) => {
            val setterMethod = rf.reflectMethod(t.setter.asMethod)

            // 型に合わせて変換
            t.getter.asMethod.returnType match {
              case t if t =:= typeOf[String] => {
                setterMethod(v)
              }
              // Primitive型は、JavaUniverse.definitionsに定義されている
              case definitions.IntTpe => {
                setterMethod(v.toInt)
              }
              case definitions.BooleanTpe => {
                setterMethod(v.toBoolean)
              }
              case t => println("Unknown type " + t)
            }
          }
          case None => println("Value not found:" + t.name.decoded)
        }
      }
    })
    v
  }

}

case class User(var name: String, var gender: Int, var admin_? : Boolean) {
  def this() = this("", 1, false)
}
 
