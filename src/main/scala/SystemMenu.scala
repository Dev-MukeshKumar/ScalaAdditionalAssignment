import generateData._
import models._
import operations._

import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}
import scala.util.{Failure, Success, Try}

object SystemMenu extends App{

  displayData()

  menu()

  @tailrec
  def menu(
            choice:Try[Option[Int]] = Success(None),
            employees: List[Employee] = InMemoryData.employees,
            departments: Map[Int,Department] = InMemoryData.departments,
            projects: Map[Int,Project] = InMemoryData.projects,
            employeesWithRole: List[EmployeeWithRole] = List.empty[EmployeeWithRole]
          ):Unit =
  {
    choice match {
      case Success(None) => {
        println("---------------------------Employee Data Stats System---------------------------")
        println("1. List project members")
        println("2. Average salary in each department")
        println("3. Delete a project")
        println("4. Delete a department")
        println("5. Add new employee data")
        println("6. Get employees based on DOJ")
        println("7. Increment salary by 10% for department 3 and 5")
        println("8. List senior and highly paid employees")
        println("9. Average salary of employees in a particular project")
        println("10. Display Roles of employee")
        println("11. Display data")
        println("\nNote: enter -1 to exit application!")
        println("----------------------------------------------------------------------------------")
        print("Enter your choice: ")
        menu(Try(Option(readInt())),employees,departments,projects,employeesWithRole)
      }
      case Success(Some(-1)) => {
        println("Thanks for using Employee Data Stats System.")
        System.exit(0)
      }
      case Success(Some(value)) if value <= -2 || value >= 12 =>{
        println("Please refer the menu and enter a valid operation number!")
        waitForPressingEnter()
        menu(choice=Success(None),employees,departments,projects,employeesWithRole)
      }
      case Success(Some(value)) if value >=1 && value <= 10 => {
        val (modifiedEmployees,modifiedDepartments,modifiedProjects,modifiedEmployeesWithRole) = OperationMapper.callRespectiveOperation(value,employees,departments,projects,employeesWithRole)
        menu(Success(None),modifiedEmployees,modifiedDepartments,modifiedProjects,modifiedEmployeesWithRole)
      }

      case Success(Some(value)) if value == 11 => {
        displayData(employees,departments,projects,employeesWithRole)
        menu(Success(None), employees,departments,projects,employeesWithRole)
      }

      case _ => {
        println("\nEnter a valid number!")
        menu(choice = Success(None),employees,departments,projects,employeesWithRole)
      }
    }
  }

  def displayData(employees: List[Employee] = InMemoryData.employees,
                  departments: Map[Int, Department] = InMemoryData.departments,
                  projects: Map[Int, Project] = InMemoryData.projects,
                  employeesWithRole: List[EmployeeWithRole] = List.empty[EmployeeWithRole]):Unit = {
    //display employee details
    println("---------------------------------Employees Data---------------------------------------")
    employees foreach println
    println

    //display department details
    println("---------------------------------Departments Data---------------------------------------")
    departments.map(department=> println(department._2))
    println

    //display project details
    println("---------------------------------Projects Data---------------------------------------")
    projects.map(project=> println(project._2))
    println

    //display employee roles
    println("---------------------------------Employees role Data---------------------------------------")
    if(employeesWithRole.isEmpty) println("Data updates after performing operation 10.") else employeesWithRole foreach println
    println

    waitForPressingEnter()
  }

  @tailrec
  def confirmDisplayData(employees: List[Employee],
                         departments: Map[Int, Department],
                         projects: Map[Int, Project],
                         employeesWithRole: List[EmployeeWithRole]): Unit = {
    println("Do you want to display data? (Yes/yes/y or No/no/n)")
    val condition = Try(readLine()(0))
    condition match {
      case Success(value) if value.toLower == 'y' => displayData(employees,departments, projects, employeesWithRole)
      case Success(value) if value.toLower == 'n' =>
      case Success(value) => {
        println("please enter only (Yes/yes/y or No/no/n)!")
        confirmDisplayData(employees, departments, projects, employeesWithRole)
      }
      case Failure(exception) => {
        println("please enter only (Yes/yes/y or No/no/n)!")
        confirmDisplayData(employees, departments, projects, employeesWithRole)
      }
    }
  }

  def waitForPressingEnter(): Unit = {
    println("\npress enter to continue.")
    readLine()
  }
}
