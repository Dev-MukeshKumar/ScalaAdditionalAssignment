import models._
import operations._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util.UUID

class Operation1Spec extends AnyFlatSpec with Matchers{

  "getProjectMembers method with existing project Id" should "return list of employees working under that project" in {
    val employees = List[Employee](Employee(UUID.randomUUID(),"mukesh",None,50000,1,Address(12,"Anna nedum pathai",None,123456),"+91 1234-123456"),
                                     Employee(UUID.randomUUID(),"maddy",None,60000,2,Address(13,"nedum pathai",None,123453),"+91 1104-123456"))
    val departments = Map(1 -> Department(1,"asdasd",Option(1001)))
    val projects = Map(1001 -> Project(1001,"asdasd"))

    val projectMembersList = Operation1.getProjectMembers(employees,departments,projects,1001).get
    assert(projectMembersList.length == 1)
  }

  "getProjectMembers method with non existing project Id" should "return None" in {
    val employees = List[Employee](Employee(UUID.randomUUID(), "mukesh", None, 50000, 1, Address(12, "Anna nedum pathai", None, 123456), "+91 1234-123456"),
      Employee(UUID.randomUUID(), "maddy", None, 60000, 2, Address(13, "nedum pathai", None, 123453), "+91 1104-123456"))
    val departments = Map(1 -> Department(1, "asdasd", Option(1001)))
    val projects = Map(1001 -> Project(1001, "asdasd"))

    val projectMembersList = Operation1.getProjectMembers(employees, departments, projects, 1002)
    assert(projectMembersList.isEmpty)
  }

  "getProjectMembers method with projects that has no mapping to department" should "return empty list" in {
    val employees = List[Employee](Employee(UUID.randomUUID(), "mukesh", None, 50000, 1, Address(12, "Anna nedum pathai", None, 123456), "+91 1234-123456"),
      Employee(UUID.randomUUID(), "maddy", None, 60000, 2, Address(13, "nedum pathai", None, 123453), "+91 1104-123456"))
    val departments = Map(1 -> Department(1, "asdasd", None))
    val projects = Map(1001 -> Project(1001, "asdasd"))

    val projectMembersList = Operation1.getProjectMembers(employees, departments, projects, 1001).get
    assert(projectMembersList.isEmpty)
  }

  "getProjectMembers method with empty employee list" should "return empty list" in {
    val employees = List.empty[Employee]
    val departments = Map(1 -> Department(1, "asdasd", Option(1)))
    val projects = Map(1001 -> Project(1001, "asdasd"))

    val projectMembersList = Operation1.getProjectMembers(employees, departments, projects, 1001).get
    assert(projectMembersList.isEmpty)
  }

}
