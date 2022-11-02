package operations

import models._

object Operation4 {
  def deleteDepartment(departmentId: Int,employees:List[Employee],departments:Map[Int,Department],employeesWithRole: List[EmployeeWithRole]): (List[Employee],Map[Int,Department],List[EmployeeWithRole]) = {
    if(!departments.contains(departmentId)) {
      println(s"Department with Id: ${departmentId} does not exists!")
      (employees,departments,employeesWithRole)
    }
    else {
      println(s"Department with Id: ${departmentId} deleted and respective employees also removed!")
      if(employeesWithRole.isEmpty) (employees.filter(employee => employee.departmentId != departmentId),
        departments.filter(department => department._1 != departmentId),employeesWithRole)
      else
        (employees.filter(employee => employee.departmentId != departmentId),
          departments.filter(department => department._1 != departmentId),
          employeesWithRole.filter(employee => employee.departmentId != departmentId))
    }
  }
}
