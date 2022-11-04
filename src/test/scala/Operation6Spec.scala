import generateData.InMemoryData
import models._
import operations.Operation6
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID

class Operation6Spec extends AnyFlatSpec with Matchers{
  "getEmployeeByDoj method" should "return list of employees with DOJ after given date" in {

    val employees = List[Employee](Employee(UUID.randomUUID(), "mukesh", None, 50000, 1, Address(12, "Anna nedum pathai", None, 123456), "+91 1234-123456"),
      Employee(UUID.randomUUID(), "maddy", Option(LocalDate.parse("1/1/2013", DateTimeFormatter.ofPattern("d/M/yyyy"))), 60000, 2, Address(13, "nedum pathai", None, 123453), "+91 1104-123456"))

    val employeeList = Operation6.getEmployeeByDoj(employees,LocalDate.parse("1/1/2010",DateTimeFormatter.ofPattern("d/M/yyyy")))
    assert(employeeList.nonEmpty)
  }

  "getEmployeeByDoj method" should "return empty list when all DOJ is before given date" in {

    val employees = List[Employee](Employee(UUID.randomUUID(), "mukesh", None, 50000, 1, Address(12, "Anna nedum pathai", None, 123456), "+91 1234-123456"),
      Employee(UUID.randomUUID(), "maddy", Option(LocalDate.parse("1/1/2013",DateTimeFormatter.ofPattern("d/M/yyyy"))), 60000, 2, Address(13, "nedum pathai", None, 123453), "+91 1104-123456"))

    val employeeList = Operation6.getEmployeeByDoj(employees, LocalDate.parse("1/1/2020", DateTimeFormatter.ofPattern("d/M/yyyy")))
    assert(employeeList.isEmpty)
  }
}
