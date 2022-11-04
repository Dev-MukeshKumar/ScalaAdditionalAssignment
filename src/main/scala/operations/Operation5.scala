package operations

import models._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}
import scala.util.{Failure, Random, Success, Try}

object Operation5 {
  def addNewEmployee(
                      employees: List[Employee],
                      departments:Map[Int,Department],
                      id:UUID =UUID.randomUUID(),
                      name:String =getEmployeeName(),
                      doj:Option[LocalDate] = getDoj(),
                      salary:Int = getSalary(),
                      address: Address = getAddress(),
                      phoneNumber:String = getPhoneNumber()
                    ): List[Employee] = {
    val departmentId = getRandomDepartment(departments)
    employees :+ Employee(id, name,doj,salary,departmentId,address,phoneNumber)
  }

  //employee details getters
  @tailrec
  def getEmployeeName():String = {
    print("Employee name: ")
    val name = readLine()
    if(name.matches("[a-zA-Z]{5,25}")) name
    else {
      println("Please enter name with alphabets only and length ranging from 5 to 25.")
      getEmployeeName()
    }
  }

  @tailrec
  def getDoj(): Option[LocalDate] = {
    print("Date of joining(dd/mm/yyyy)\n(Optional input, to skip press enter): ")
    val dateOfJoining = readLine()
    (isValidDate(dateOfJoining),dateOfJoining) match {
      case (false,"") => None
      case (false,_) => {
        println("Please enter date with format(dd/mm/yyyy)!")
        getDoj()
      }
      case (true,value) => {
        val defaultDate = LocalDate.parse("1/1/2010",DateTimeFormatter.ofPattern("d/M/yyyy"))
        val inputDate = LocalDate.parse(value,DateTimeFormatter.ofPattern("d/M/yyyy"))
        val currentDate = LocalDate.now()
        if( inputDate.isBefore(defaultDate) ) {
          println("please enter date after the year 2009!")
          getDoj()
        }
        else if(inputDate.isAfter(currentDate)) {
          println("please enter possible date of joining not future date!")
          getDoj()
        }
        else {
          Option(inputDate)
        }
      }
    }
  }

  @tailrec
  def getSalary(): Int = {
    print("salary: ")
    val salary = Try(readInt())
    salary match {
      case Success(value) if isValidSalary(value) => value
      case Success(value) if !isValidSalary(value) => {
        println("Please enter salary in the range 40000 to 90000 and multiple of 2000!")
        getSalary()
      }
      case Failure(exception) => {
        println("Please enter numbers only!")
        getSalary()
      }
    }
  }

  def getAddress(): Address = Address(getStreetNumber(),getAreaName(),getStateName(),getPinCode())

  @tailrec
  def getPhoneNumber(): String = {
    print("Phone number (+91 xxxx-yyyyyy): ")
    val phoneNumber = readLine()
    if (phoneNumber.matches("^(\\+91)\\s([1-9][0-9]{3})-([0-9]{6})$")) phoneNumber
    else {
      println("Please follow the format +91 xxxx-yyyyyy")
      getPhoneNumber()
    }
  }

  def getRandomDepartment(departments: Map[Int,Department]):Int = {
    val departmentKeys = departments.keys.toList
    departmentKeys(0+Random.nextInt(departmentKeys.length))
  }

  //address details getters

  @tailrec
  def getStreetNumber():Int = {
    print("Street number: ")
    val streetNumber = Try(readInt())
    streetNumber match {
      case Success(value) => value
      case Failure(exception) => {
        println("Please enter numbers only!")
        getStreetNumber()
      }
    }
  }

  @tailrec
  def getAreaName():String = {
    print("Area name: ")
    val areaName = readLine()
    if (areaName.matches("^[a-zA-Z\\s]*$")) areaName
    else {
      println("Please enter alphabets only!")
      getAreaName()
    }
  }

  @tailrec
  def getStateName(): Option[String] = {
    print("State\n(Optional input, to skip press enter):")
    val stateName = Option(readLine())
    stateName match {
      case Some(value) if value.trim == "" => None
      case Some(value) if !value.matches("^[a-zA-Z\\s]*$") => {
        println("Please enter alphabets only!")
        getStateName()
      }
      case Some(value) => Some(value.trim)
    }
  }

  @tailrec
  def getPinCode(): Int ={
    print("picode: ")
    val pinCode = Try(readInt())
    pinCode match {
      case Success(value) if value.toString.matches("^[1-9][0-9]{5}$") => value
      case Success(value) if !value.toString.matches("^[1-9][0-9]{5}$") => {
        println("Please enter a 6 digit pin code!")
        getPinCode()
      }
      case Failure(exception) => {
        println("Please enter numbers only!")
        getPinCode()
      }
    }
  }

  //validate date format
  def isValidDate(input: String): Boolean = Try(LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"))) match {
    case Success(value) => true
    case _ => false
  }

  //validate salary constrain
  def isValidSalary(salary: Int): Boolean = salary>=40000 && salary <=90000 && salary%2000 == 0

}
