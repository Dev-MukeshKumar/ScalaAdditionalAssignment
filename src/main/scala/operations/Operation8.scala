package operations

import models._

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Operation8 {

  def displayHighestPaidEmployee(employees: List[Employee]):Unit = {
    println("\n---------------------------------Top 5 Highly Paid Employees Data---------------------------------------")
    employees.sortWith(_.salary > _.salary).take(5).foreach(employee => {
      println(employee.name+" "+employee.salary)
    })
  }

  def displaySeniorMostEmployeeByDoj(employees: List[Employee]): Unit = {
    println("\n---------------------------------Top 5 Senior Employees Data---------------------------------------")
    employees.sortWith((x,y) => getDate(x.doj).isBefore(getDate(y.doj))).take(5).foreach(employee => {
      println(employee.name + " " + getDate(employee.doj))
    })
  }


  def getDate(date:Option[LocalDate]): LocalDate = date.getOrElse(LocalDate.parse("1/1/2010",DateTimeFormatter.ofPattern("d/M/yyyy")))
}
