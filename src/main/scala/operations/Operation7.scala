package operations

import models.Employee

object Operation7 {
  def incrementSalaryBy10PercentForTwoDepartments(employees: List[Employee]): List[Employee] = {
    employees.map(x => {
      (x.departmentId == 3 || x.departmentId == 5, x) match {
        case (true, Employee(id, name, doj, salary, departmentId, address, phoneNumber)) => {
          Employee(id, name, doj, (salary + (salary * 0.1)).toInt, departmentId, address, phoneNumber)
        }
        case _ => x
      }
    })
  }
}
