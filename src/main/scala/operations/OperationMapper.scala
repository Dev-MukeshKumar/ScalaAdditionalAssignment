package operations

import models._

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}
import scala.io.StdIn.{readInt, readLine}

object OperationMapper {

  @tailrec
  def callRespectiveOperation(n: Int, employees: List[Employee], departments: Map[Int, Department], projects: Map[Int, Project]): (List[Employee], Map[Int, Department], Map[Int, Project]) = n match {
    case 1 => {
      print("Enter project id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) => {
          Operation1.getProjectMembers(employees, departments, projects, value)
          waitForPressingEnter()
          (employees, departments, projects)
        }
        case Failure(value) => {
          println("Please enter a valid project id!")
          OperationMapper.callRespectiveOperation(1, employees, departments, projects)
        }
      }
    }

    case 2 => {
      Operation2.getAverageSalaryInDepartments(employees,departments)
      waitForPressingEnter()
      (employees, departments, projects)
    }

    case 3 => {
      print("Enter project id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) =>{
          val (a,b) = Operation3.deleteProject(value, departments, projects)
          waitForPressingEnter()
          (employees, a, b)
        }
        case Failure(value) =>{
          println("Please enter a valid project id!")
          OperationMapper.callRespectiveOperation(3,employees,departments,projects)
        }
      }
    }

    case 4 => {
      print("Enter department id: ")
      val projectId = Try(readInt())
      projectId match {
        case Success(value) => {
          val (a, b) = Operation4.deleteDepartment(value, employees,departments)
          waitForPressingEnter()
          (a,b,projects)
        }
        case Failure(value) => {
          println("Please enter a valid department id!")
          OperationMapper.callRespectiveOperation(4,employees,departments,projects)
        }
      }
    }

    case _ if List.range(4, 11).contains(n.toInt) => {
      println("Not implemented yet, please try later!")
      waitForPressingEnter()
      (employees, departments, projects)
    }
    case _ => {
      println("Please refer the menu and enter a valid number!")
      waitForPressingEnter()
      (employees, departments, projects)
    }
  }

  def waitForPressingEnter(): Unit ={
    println("\npress enter to continue.")
    readLine()
  }
}
