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
          val listOfEmployees = Operation1.getProjectMembers(employees, departments, projects, value) match {
            case Some(value) if value.nonEmpty => {
              println("-----Employee names-----")
              value.foreach(x => println(x.name))
            }
            case Some(value) if value.isEmpty => println("No employees are currently working under this project.")
            case _ => println(s"Project with Id: ${projectId.get} does not exists!")
          }
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
          val (modifiedDepartments,modifiedProjects) = Operation3.deleteProject(value, departments, projects)
          waitForPressingEnter()
          (employees,modifiedDepartments,modifiedProjects,employeesWithRole)
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
          val (modifiedEmployees, modifiedDepartments, modifiedEmployeesWithRole) = Operation4.deleteDepartment(value, employees,departments,employeesWithRole)
          waitForPressingEnter()
          (modifiedEmployees,modifiedDepartments,projects,modifiedEmployeesWithRole)
        }
        case Failure(value) => {
          println("Please enter a valid department id!")
          OperationMapper.callRespectiveOperation(4,employees,departments,projects,employeesWithRole)
        }
      }
    }

    case 5 => {
      val modifiedEmployees = Operation5.addNewEmployee(employees, departments)
      println("Employee with following details added successfully: "+modifiedEmployees.last)
      waitForPressingEnter()
      (modifiedEmployees, departments, projects,employeesWithRole)
    }

    case 6 => {
      val filteredEmployeeList = Operation6.getEmployeeByDoj(employees)
      if (filteredEmployeeList.isEmpty) {
        println(s"No employees are found, please enter a date prior!")
        OperationMapper.callRespectiveOperation(6, employees, departments, projects,employeesWithRole)
      } else {
        println("\n---------------------------------Employees Data---------------------------------------")
        filteredEmployeeList.foreach(employee => println(employee.id + " - " + employee.name))
        waitForPressingEnter()
        (employees, departments, projects, employeesWithRole)
      }
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
        val modifiedEmployees = Operation7.incrementSalaryBy10PercentForTwoDepartments(employees)
        println("---------------------------------Employees Data---------------------------------------")
        modifiedEmployees.foreach(employee => println(employee.name+" - "+employee.salary))
        waitForPressingEnter()
        (modifiedEmployees, departments, projects,employeesWithRole)
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
          val averageSalary = Operation9.getAverageSalaryOnProject(value, employees, departments, projects)
          averageSalary match {
            case -1 => println(s"Project with Id: ${value} does not exists")
            case 0 => println("Currently the project is not having members!")
            case _ => println(s"The project with Id: ${value} have the average salary: ${averageSalary}.")
          }
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
      val modifiedEmployeesWithRole = Operation10.getEmployeesWithRole(employees)
      println("---------------------------------Employees Data with roles---------------------------------------")
      modifiedEmployeesWithRole foreach println
      waitForPressingEnter()
      (employees, departments, projects, modifiedEmployeesWithRole)
    }

    case 11 => (employees, departments, projects, employeesWithRole)

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
