package operations

import models._

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Operation10 {

  def getEmployeesWithRole(employees: List[Employee]): List[EmployeeWithRole] = {
    val defaultDate = LocalDate.parse("01/01/2010", DateTimeFormatter.ofPattern("d/M/yyyy"))
    employees.map(employee => EmployeeWithRole(employee.id, employee.departmentId, getRole(employee.doj.getOrElse(defaultDate))))
  }

  def getRole(doj:LocalDate):String = {

    //calculating years of experience and deciding role
    (LocalDate.now().toEpochDay() - doj.toEpochDay()) / 365 match {
      case years if years <=1 => "Junior analyst"
      case years if(years>1 && years<=3) =>  "Analyst"
      case years if(years>3 && years<=5) => "Senior analyst"
      case years if(years>5 && years<=7) => "Lead analyst"
      case _ => "Chief analyst"
    }
  }

}
