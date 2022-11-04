import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import operations._
import generateData.InMemoryData
import models.Address

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

class Operation5Spec extends AnyFlatSpec with Matchers{
  val employees = InMemoryData.employees
  val departments = InMemoryData.departments

  "addNewEmployee method" should "add new employee data with DOJ and state value null type" in {
    val newEmployeeList = Operation5.addNewEmployee(
      employees,
      departments,
      UUID.randomUUID(),
      "mukesh",
      None,
      50000,
      Address(12,"asdasd",None,123456),
      "+91 1234-123456"
    )

    assert(newEmployeeList.length == employees.length + 1)
  }

  it should "add new employee data with DOJ value null type" in {
    val newEmployeeList = Operation5.addNewEmployee(
      employees,
      departments,
      UUID.randomUUID(),
      "mukesh",
      None,
      50000,
      Address(12, "asdasd", Option("Test"), 123456),
      "+91 1234-123456"
    )

    assert(newEmployeeList.length == employees.length + 1)
  }

  it should "add new employee with all valid input" in {
    val newEmployeeList = Operation5.addNewEmployee(
      employees,
      departments,
      UUID.randomUUID(),
      "mukesh",
      Option(LocalDate.parse("1/1/2017",DateTimeFormatter.ofPattern("d/M/yyyy"))),
      50000,
      Address(12, "asdasd", Option("Test"), 123456),
      "+91 1234-123456"
    )

    assert(newEmployeeList.length == employees.length + 1)
  }

}
