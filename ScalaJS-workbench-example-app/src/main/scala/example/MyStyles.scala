package example

import CssSettings._

object MyStyles extends StyleSheet.Inline {
  import dsl._

  val bootstrapButton = style(
    addClassNames("btn", "btn-default"),
    fontSize(200 %%)
  )
}
