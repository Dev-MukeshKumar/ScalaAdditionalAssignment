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

    //calculating years of experience
    val years = (LocalDate.now().toEpochDay() - doj.toEpochDay()) / 365

    if(years<=1) "Junior analyst"
    else if(years>1 && years<=3) "Analyst"
    else if(years>3 && years<=5) "Senior analyst"
    else if(years>5 && years<=7) "Lead analyst"
    else "Chief analyst"
  }

}
