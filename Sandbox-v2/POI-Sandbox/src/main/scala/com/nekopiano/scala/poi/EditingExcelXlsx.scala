package com.nekopiano.scala.poi

import java.io.{File, FileInputStream, FileOutputStream}
import java.nio.file.Files.copy
import java.nio.file.{Files, Paths, StandardCopyOption}

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFWorkbook

/**
  * Created on 23/11/2016.
  */
object EditingExcelXlsx extends App {

  copy(Paths.get(getClass.getResource("/Workbook-testing.xlsx").getPath), Paths.get("Workbook-testing-edited.xlsx"), StandardCopyOption.REPLACE_EXISTING)

  val fis = new FileInputStream(new File("Workbook-testing-edited.xlsx"))
  val wb = new XSSFWorkbook(fis)
  val sheet = wb.getSheetAt(0)

  val cell = sheet.createRow(1).createCell(1)
  cell.setCellType(CellType.FORMULA)
  cell.setCellFormula("3.14*2")
  //sheet.getForceFormulaRecalculation
  val fos = new FileOutputStream(new File("Workbook-testing-edited.xlsx"))
  wb.write(fos)


  wb.close()
}
