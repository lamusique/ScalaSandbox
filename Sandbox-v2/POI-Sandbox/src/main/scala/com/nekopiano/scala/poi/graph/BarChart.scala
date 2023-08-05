package com.nekopiano.scala.poi.graph

import java.io.FileOutputStream

import org.apache.poi.ss.usermodel._
import org.apache.poi.ss.util._
import org.apache.poi.ss.usermodel.charts._
import org.apache.poi.xssf.usermodel.{XSSFCell, XSSFChart, XSSFWorkbook}
import org.openxmlformats.schemas.drawingml.x2006.chart._

import scala.util.Random

// https://stackoverflow.com/questions/38913412/create-bar-chart-in-excel-with-apache-poi

object BarChart {

  def main(args: Array[String]): Unit = {
    val wb = new XSSFWorkbook
    val sheet = wb.createSheet("Sheet1")
    //var row = null
    var cell: XSSFCell = null
    var row = sheet.createRow(0)
    row.createCell(0)
    row.createCell(1).setCellValue("HEADER 1")
    row.createCell(2).setCellValue("HEADER 2")
    row.createCell(3).setCellValue("HEADER 3")
    for (r <- 1 until 5) {
      row = sheet.createRow(r)
      cell = row.createCell(0)
      cell.setCellValue("Serie " + r)
      cell = row.createCell(1)
      cell.setCellValue(new Random().nextDouble)
      cell = row.createCell(2)
      cell.setCellValue(new Random().nextDouble)
      cell = row.createCell(3)
      cell.setCellValue(new Random().nextDouble)

    }
    val drawing = sheet.createDrawingPatriarch
    val anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 8, 20)
    val chart = drawing.createChart(anchor)

    val ctChart = chart.asInstanceOf[XSSFChart].getCTChart
    val ctPlotArea = ctChart.getPlotArea
    val ctBarChart = ctPlotArea.addNewBarChart

    // stacked bar chart
    val barGrp = ctBarChart.addNewGrouping()
    barGrp.setVal(STBarGrouping.STACKED)


    val ctBoolean = ctBarChart.addNewVaryColors
    ctBoolean.setVal(true)
    ctBarChart.addNewBarDir.setVal(STBarDir.COL)

    for (r <- 2 until 6) {
      val ctBarSer = ctBarChart.addNewSer
      val ctSerTx = ctBarSer.addNewTx
      var ctStrRef = ctSerTx.addNewStrRef
      ctStrRef.setF("Sheet1!$A$" + r)
      ctBarSer.addNewIdx.setVal(r - 2)
      val cttAxDataSource = ctBarSer.addNewCat
      ctStrRef = cttAxDataSource.addNewStrRef
      ctStrRef.setF("Sheet1!$B$1:$D$1")
      val ctNumDataSource = ctBarSer.addNewVal
      val ctNumRef = ctNumDataSource.addNewNumRef
      ctNumRef.setF("Sheet1!$B$" + r + ":$D$" + r)
      //at least the border lines in Libreoffice Calc ;-)
      ctBarSer.addNewSpPr.addNewLn.addNewSolidFill.addNewSrgbClr.setVal(Array[Byte](0, 0, 0))

    }
    //telling the BarChart that it has axes and giving them Ids
    ctBarChart.addNewAxId.setVal(123456)
    ctBarChart.addNewAxId.setVal(123457)
    //cat axis
    val ctCatAx = ctPlotArea.addNewCatAx
    ctCatAx.addNewAxId.setVal(123456) //id of the cat axis

    var ctScaling = ctCatAx.addNewScaling
    ctScaling.addNewOrientation.setVal(STOrientation.MIN_MAX)
    ctCatAx.addNewDelete.setVal(false)
    ctCatAx.addNewAxPos.setVal(STAxPos.B)
    ctCatAx.addNewCrossAx.setVal(123457) //id of the val axis

    ctCatAx.addNewTickLblPos.setVal(STTickLblPos.NEXT_TO)
    //val axis
    val ctValAx = ctPlotArea.addNewValAx
    ctValAx.addNewAxId.setVal(123457)
    ctScaling = ctValAx.addNewScaling
    ctScaling.addNewOrientation.setVal(STOrientation.MIN_MAX)
    ctValAx.addNewDelete.setVal(false)
    ctValAx.addNewAxPos.setVal(STAxPos.L)
    ctValAx.addNewCrossAx.setVal(123456)
    ctValAx.addNewTickLblPos.setVal(STTickLblPos.NEXT_TO)
    //legend
    val ctLegend = ctChart.addNewLegend
    ctLegend.addNewLegendPos.setVal(STLegendPos.B)
    ctLegend.addNewOverlay.setVal(false)
    System.out.println(ctChart)
    val fileOut = new FileOutputStream("BarChart.xlsx")
    wb.write(fileOut)
    fileOut.close()
  }
}
