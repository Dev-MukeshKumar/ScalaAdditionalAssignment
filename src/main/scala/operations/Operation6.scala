package operations

import models.Employee

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.{Success, Try}

object Operation6 {

  @tailrec
  def displayEmployeeByDoj(employees:List[Employee]): Unit = {
    val date = getDateInput()
    val defaultDate = LocalDate.parse("01/01/2010",DateTimeFormatter.ofPattern("d/M/yyyy"))

    val filteredEmployeeList = employees.filter(employee => dateGreaterThanOrEqual(employee.doj.getOrElse(defaultDate),date))

    if(filteredEmployeeList.isEmpty) {
      println(s"No employees are found, please enter a date prior to ${date.format(DateTimeFormatter.ofPattern("d/M/yyyy"))}!")
      displayEmployeeByDoj(employees)
    } else {
      println("\n---------------------------------Employees Data---------------------------------------")
      filteredEmployeeList.foreach(employee => println(employee.id +" - "+ employee.name))
    }
  }

  //helper methods

  //method to get input and check constrain
  @tailrec
  def getDateInput(): LocalDate = {
    print("Enter a date(dd/mm/yyyy): ")
    val dateOfJoining = readLine()
    (isValidDate(dateOfJoining), dateOfJoining) match {
      case (false, _) => {
        println("Please enter date with format(dd/mm/yyyy)!")
        getDateInput()
      }
      case (true, value) => {
        val year = value.split("/")(2).toInt
        val currentYear = LocalDate.now().getYear
        if (year < 2010) {
          println("please enter date after the year 2009!")
          getDateInput()
        }
        else if (year > currentYear) {
          println("please don't enter future date!")
          getDateInput()
        }
        else {
          LocalDate.parse(value, DateTimeFormatter.ofPattern("d/M/yyyy"))
        }
      }
    }
  }

  //date comparison method
  def dateGreaterThanOrEqual(inputDate: LocalDate,constrain: LocalDate):Boolean = inputDate.isAfter(constrain) || inputDate.isEqual(constrain)

  //validate input date format
  def isValidDate(input: String): Boolean = Try(LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"))) match {
    case Success(value) => true
    case _ => false
  }

}
