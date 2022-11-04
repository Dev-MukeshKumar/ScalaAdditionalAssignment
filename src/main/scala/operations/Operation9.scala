package operations

import models._

import scala.annotation.tailrec

object Operation9 {
  def getAverageSalaryOnProject(projectId: Int, employees:List[Employee], departments:Map[Int,Department], projects:Map[Int,Project]):Int = {
    if(!projects.contains(projectId)) -1
    else {
      val departmentId = getDepartmentIdByProjectId(projectId,departments.values.toList)
      departmentId match {
        case Some(value) => {
          val employeesList = getEmployeesByDepartmentId(value,employees)
          if(employeesList.isEmpty) 0
          else{
            getSalarySum(employeesList) / employeesList.length
          }
        }
        case None => 0
      }
    }
  }

  @tailrec
  def getDepartmentIdByProjectId(projectId:Int, departments: List[Department]):Option[Int] = departments match {
      case departments if departments.head.projectId.contains(projectId) => Some(departments.head.id)
      case departments if departments.tail.nonEmpty => getDepartmentIdByProjectId(projectId,departments.tail)
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
