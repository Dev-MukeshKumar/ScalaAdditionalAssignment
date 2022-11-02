import generateData._
import models._
import operations._
import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine}
import scala.util.{Success, Try}

object SystemMenu extends App{

  //display employee details
  println("---------------------------------Employees Data---------------------------------------")
  InMemoryData.employees foreach println
  println

  //display department details
  println("---------------------------------Departments Data---------------------------------------")
  InMemoryData.departments foreach println
  println

  //display project details
  println("---------------------------------Projects Data---------------------------------------")
  InMemoryData.projects foreach println
  println

  waitForPressingEnter()

  menu()

  @tailrec
  def menu(
            choice:Try[Option[Int]] = Success(None),
            employees: List[Employee] = InMemoryData.employees,
            departments: Map[Int,Department] = InMemoryData.departments,
            projects: Map[Int,Project] = InMemoryData.projects,
            employeesWithRole: List[EmployeeWithRole] = List.empty[EmployeeWithRole]
          ):Any =
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
        println("\nNote: enter -1 to exit application!")
        println("----------------------------------------------------------------------------------")
        print("Enter your choice: ")
        menu(Try(Option(readInt())),employees,departments,projects)
      }
      case Success(Some(-1)) => {
        println("Thanks for using Employee Data Stats System.")
        System.exit(0)
      }
      case Success(Some(value)) if value <= -2 || value >= 11 =>{
        println("Please refer the menu and enter a valid operation number!")
        println("\npress enter to continue.")
        readLine()
        menu(choice=Success(None),employees,departments,projects,employeesWithRole)
      }
      case Success(Some(value)) if value >=1 && value <= 10 => {
        val (a,b,c,d) = OperationMapper.callRespectiveOperation(value,employees,departments,projects,employeesWithRole)
        menu(Success(None),a,b,c,d)
      }
      case _ => {
        println("Enter a valid number!")
        menu(choice = Success(None),employees,departments,projects,employeesWithRole)
      }
    }
  }

  def waitForPressingEnter(): Unit = {
    println("\npress enter to continue.")
    readLine()
  }
}
