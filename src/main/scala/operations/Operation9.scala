package operations

import models._

import scala.annotation.tailrec

object Operation9 {
  def getAverageSalaryOnProject(projectId: Int, employees:List[Employee], departments:Map[Int,Department], projects:Map[Int,Project]):Unit = {
    if(!projects.contains(projectId)) println(s"Project with Id:${projectId} does not exists!")
    else {
      val departmentId = getDepartmentIdByproductId(projectId,departments.values.toList)
      departmentId match {
        case Some(value) => {
          val employeesList = getEmployeesByDepartmentId(value,employees)
          if(employeesList.isEmpty) println(s"There are no employees working under this department with Id: ${value}")
          else{
            val averageSalary = getSalarySum(employeesList) / employeesList.length
            println(s"The average salary under the project with Id:${projectId} is ${averageSalary}")
          }
        }
        case None => println(s"No department is working under this project with Id: ${projectId}")
      }
    }
  }

  @tailrec
  def getDepartmentIdByproductId(projectId:Int, departments: List[Department]):Option[Int] = departments match {
      case departments if departments.head.projectId == projectId => Some(departments.head.id)
      case departments if !departments.tail.isEmpty => getDepartmentIdByproductId(projectId,departments.tail)
      case _ => None
  }

  @tailrec
  def getEmployeesByDepartmentId(departmentId:Int,employees:List[Employee],acc:List[Employee] = List.empty[Employee]):List[Employee] = employees match {
      case employees if employees.isEmpty => acc
      case employees if employees.head.departmentId == departmentId => getEmployeesByDepartmentId(departmentId,employees.tail,acc :+ employees.head)
      case _ => getEmployeesByDepartmentId(departmentId,employees.tail,acc)
  }

  @tailrec
  def getSalarySum(employees:List[Employee],acc:Int = 0):Int = employees match {
    case employees if employees.isEmpty => acc
    case _ => getSalarySum(employees.tail, acc + employees.head.salary)
  }


}
