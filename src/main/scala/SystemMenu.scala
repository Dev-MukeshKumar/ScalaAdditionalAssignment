import generate._
import models._
import operations._

import scala.annotation.tailrec
import scala.io.StdIn.readInt
import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object SystemMenu extends App{

  DataStore.employees foreach println

  menu()

  @tailrec
  def menu(
            choice:Try[Option[Int]] = Success(None),
            employees: List[Employee] = DataStore.employees,
            departments: Map[Int,Department] = DataStore.departments,
            projects: Map[Int,Project] = DataStore.projects
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
        println("9. Average salary of employee in a particular project")
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
        menu(choice=Success(None),employees,departments,projects)
      }
      case Success(Some(value)) if value >=1 && value <= 10 => {
        val (a,b,c) = OperationMapper.callRespectiveOperation(value,employees,departments,projects)
        menu(Success(None),a,b,c)
      }
      case _ => {
        println("Enter a valid number!")
        menu(choice = Success(None))
      }
    }
  }
}
