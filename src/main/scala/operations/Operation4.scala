package operations

import models._

object Operation4 {
  def deleteDepartment(departmentId: Int,employees:List[Employee],departments:Map[Int,Department]): (List[Employee],Map[Int,Department]) = {
    if(!departments.contains(departmentId)) {
      println(s"Department with Id: ${departmentId} does not exists!")
      (employees,departments)
    }
    else {
      println(s"Department with Id: ${departmentId} deleted and respective employees also removed!")
      (employees.filter(employee => employee.departmentId != departmentId),
        departments.filter(department => department._1 != departmentId))
    }
  }
}
