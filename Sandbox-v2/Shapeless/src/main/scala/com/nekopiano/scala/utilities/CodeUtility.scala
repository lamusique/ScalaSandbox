package com.nekopiano.scala.utilities

object CodeUtility {

  def inspectFull[V](value: sourcecode.Text[V])(implicit name: sourcecode.Name,
                                                fullName: sourcecode.FullName,
                                                enclosing: sourcecode.Enclosing,
                                                args: sourcecode.Args,
                                                pkg: sourcecode.Pkg,
                                                file: sourcecode.File,
                                                line: sourcecode.Line) =
    "@[" +
      "name: " + name + ", " +
      "fullName: " + fullName + ", " +
      "enclosing: " + enclosing + ", " +
      "args: " + args + ", " +
      "pkg: " + pkg + ", " +
      "file: " + file + ", " +
      "line: " + line + ", " +
      "] value: " + value.value

  def inspect[V](value: sourcecode.Text[V])(implicit name: sourcecode.Name, line: sourcecode.Line, file: sourcecode.File) =
    name.value + " @l." + line.value + " [" + value.source + "]: " + value.value

  def kv[V](value: sourcecode.Text[V]) = value.source + "=" + value.value

}
