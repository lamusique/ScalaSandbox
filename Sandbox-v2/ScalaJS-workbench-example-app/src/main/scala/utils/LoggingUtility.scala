package utils

object LoggingUtility {

  def inspect[V](value: sourcecode.Text[V])(implicit name: sourcecode.Name) =
    name.value + " [" + value.source + "]: " + value.value

}
