package com.nekopiano.scala.poi

import java.io.{File, FileInputStream, FileOutputStream}
import java.nio.file.Files.copy
import java.nio.file.{Paths, StandardCopyOption}

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
  * Created on 23/11/2016.
  */
object EditingExcelXls extends App {

  copy(Paths.get(getClass.getResource("/Workbook-testing.xls").getPath), Paths.get("Workbook-testing-edited.xls"), StandardCopyOption.REPLACE_EXISTING)

  val fis = new FileInputStream(new File("Workbook-testing-edited.xls"))
  val wb = new HSSFWorkbook(fis)
  val sheet = wb.getSheetAt(0)

  val cell = sheet.createRow(1).createCell(1)
  cell.setCellType(CellType.FORMULA)
  cell.setCellFormula("3.14*2")
  //sheet.getForceFormulaRecalculation
  val fos = new FileOutputStream(new File("Workbook-testing-edited.xls"))
  wb.write(fos)


  wb.close()
}
