import generateData.InMemoryData
import models.{Department, Employee, EmployeeWithRole}
import operations.{Operation10, Operation4}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class Operation4Spec extends AnyFlatSpec with Matchers{

  val employees: List[Employee] = InMemoryData.employees
  val departments: Map[Int, Department] = InMemoryData.departments
  val employeesWithRole: List[EmployeeWithRole] = Operation10.getEmployeesWithRole(employees)

  "deleteDepartment method with existing department Id input" should "removes the respective department and its employees" in {

    val employeesCountOfDepartmentId1 = employees.count(employee => employee.departmentId == 1)

    val (afterDeleteEmployees, afterDeleteDepartments, afterDeleteEmployeesWithRole) = Operation4.deleteDepartment(1,employees,departments,employeesWithRole)
    afterDeleteDepartments.contains(1) should be(false)
    afterDeleteEmployees.length should be(employees.length - employeesCountOfDepartmentId1)
    afterDeleteEmployeesWithRole.length should be(employees.length - employeesCountOfDepartmentId1)
  }

  "deleteDepartment method with not existing department Id input" should "returns unchanged data" in {

    val (notModifiedEmployees,notModifiedDepartments,notModifiedEmployeesWithRole) = Operation4.deleteDepartment(11,employees,departments, employeesWithRole)

    notModifiedDepartments.contains(11) should be(false)
    notModifiedDepartments.size should be(departments.size)
    notModifiedEmployees.length should be(employees.length)
    notModifiedEmployeesWithRole.length should be(employeesWithRole.length)
  }

}
