import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import operations._
import models._

import java.util.UUID

class Operation9Spec extends AnyFlatSpec with Matchers {

  val employees: List[Employee] = List[Employee](
    Employee(UUID.randomUUID(), "asdasd", None, 40000, 1, Address(12, "asdasd", None, 123456), "+91 1234-12345"),
    Employee(UUID.randomUUID(), "asdasd", None, 50000, 1, Address(12, "asdasd", None, 123456), "+91 1234-12345"),
    Employee(UUID.randomUUID(), "asdasd", None, 60000, 1, Address(12, "asdasd", None, 123456), "+91 1234-12345"),
  )

  val departments: Map[Int, Department] = Map[Int, Department](1 -> Department(1, "asd", Option(1001)), 2 -> Department(2, "aws", Option(1002)))

  val projects: Map[Int, Project] = Map[Int, Project](1001 -> Project(1001, "qwe"), 1002 -> Project(2002, "asf"))

  "getAverageSalaryOnProject method" should "return -1 if project with given Id does not exists" in {
    Operation9.getAverageSalaryOnProject(1003,employees,departments,projects) should be(-1)
  }

  "getAverageSalaryOnProject method" should "return 0 if project with no department or employees" in {
    Operation9.getAverageSalaryOnProject(1002, employees, departments, projects) should be(0)
  }

  "getAverageSalaryOnProject method" should "return average salary of employees under the project" in {
    Operation9.getAverageSalaryOnProject(1001, employees, departments, projects) should be(50000)
  }

}
