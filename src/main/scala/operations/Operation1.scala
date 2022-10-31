package operations

import models._

object Operation1 {

  def getProjectMembers(employees: List[Employee],departments:Map[Int,Department],projects:Map[Int,Project],projectId:Int): Unit ={
    if(!projects.contains(projectId)) println(s"Project id: $projectId does not exists!")
    else {
      val departmentData = departments.filter(x => x._2.projectId == projectId)
      if(departmentData.isEmpty) println("No department found for the given project!")

      val listOfEmployees = employees.filter(x => x.departmentId == departmentData.head._1)
      if(listOfEmployees.isEmpty) println("No employees working under this project.")
      else {
        println("-----Employee names-----")
        listOfEmployees.foreach(x => println(x.name))
      }
    }
  }

}
