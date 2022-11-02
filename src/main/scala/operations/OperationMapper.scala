package operations

import models._

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}
import scala.io.StdIn.{readInt, readLine}

object OperationMapper {

  @tailrec
  def callRespectiveOperation(n: Int, employees: List[Employee], departments: Map[Int, Department], projects: Map[Int, Project],employeesWithRole: List[EmployeeWithRole]): (List[Employee], Map[Int, Department], Map[Int, Project],List[EmployeeWithRole]) = n match {
    case 1 => {
      print("Enter project id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) => {
          Operation1.getProjectMembers(employees, departments, projects, value)
          waitForPressingEnter()
          (employees, departments, projects,employeesWithRole)
        }
        case Failure(value) => {
          println("Please enter a valid project id!")
          OperationMapper.callRespectiveOperation(1, employees, departments, projects,employeesWithRole)
        }
      }
    }

    case 2 => {
      Operation2.getAverageSalaryInDepartments(employees,departments)
      waitForPressingEnter()
      (employees, departments, projects,employeesWithRole)
    }

    case 3 => {
      print("Enter project id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) =>{
          val (a,b) = Operation3.deleteProject(value, departments, projects)
          waitForPressingEnter()
          (employees, a, b,employeesWithRole)
        }
        case Failure(value) =>{
          println("Please enter a valid project id!")
          OperationMapper.callRespectiveOperation(3,employees,departments,projects,employeesWithRole)
        }
      }
    }

    case 4 => {
      print("Enter department id: ")
      val departmentId = Try(readInt())
      departmentId match {
        case Success(value) => {
          val (a, b, c) = Operation4.deleteDepartment(value, employees,departments,employeesWithRole)
          waitForPressingEnter()
          (a,b,projects,c)
        }
        case Failure(value) => {
          println("Please enter a valid department id!")
          OperationMapper.callRespectiveOperation(4,employees,departments,projects,employeesWithRole)
        }
      }
    }

    case 5 => {
      val a = Operation5.addNewEmployee(employees, departments)
      println("Employee with following details added successfully: "+a(a.length-1))
      waitForPressingEnter()
      (a, departments, projects,employeesWithRole)
    }

    case 6 => {
      Operation6.displayEmployeeByDoj(employees)
      waitForPressingEnter()
      (employees, departments, projects, employeesWithRole)
    }

    case 7 => {
      if(!departments.contains(3)){
        println("Department with Id: 3 does not exists!")
        waitForPressingEnter()
        (employees, departments, projects,employeesWithRole)
      }
      else if(!departments.contains(5)){
        println("Department with Id: 5 does not exists!")
        waitForPressingEnter()
        (employees, departments, projects,employeesWithRole)
      }
      else{
        val a = Operation7.incrementSalaryBy10PercentForTwoDepartments(employees)
        println("---------------------------------Employees Data---------------------------------------")
        a.foreach(employee => println(employee.name+" - "+employee.salary))
        waitForPressingEnter()
        (a, departments, projects,employeesWithRole)
      }
    }

    case 8 => {
      Operation8.displaySeniorMostEmployeeByDoj(employees)
      Operation8.displayHighestPaidEmployee(employees)
      waitForPressingEnter()
      (employees, departments, projects, employeesWithRole)
    }

    case 9 => {
      print("Enter project id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) => {
          Operation9.getAverageSalaryOnProject(value, employees, departments, projects)
          waitForPressingEnter()
          (employees,departments,projects,employeesWithRole)
        }
        case Failure(value) => {
          println("Please enter a valid project id!")
          OperationMapper.callRespectiveOperation(9, employees, departments, projects,employeesWithRole)
        }
      }
    }

    case 10 => {
      val a = Operation10.getEmployeesWithRole(employees)
      println("---------------------------------Employees Data with roles---------------------------------------")
      a foreach println
      waitForPressingEnter()
      (employees, departments, projects, a)
    }

//    case _ if List.range(1, 11).contains(n.toInt) => {
//      println("Not implemented yet, please try later!")
//      waitForPressingEnter()
//      (employees, departments, projects,employeesWithRole)
//    }

    case _ => {
      println("Please refer the menu and enter a valid number!")
      waitForPressingEnter()
      (employees, departments, projects,employeesWithRole)
    }
  }

  def waitForPressingEnter(): Unit ={
    println("\npress enter to continue.")
    readLine()
  }
}
