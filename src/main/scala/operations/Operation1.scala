package operations

import models._

object Operation1 {

  def getProjectMembers(employees: List[Employee],departments:Map[Int,Department],projects:Map[Int,Project],projectId:Int): Option[List[Employee]] ={
    if(!projects.contains(projectId)) None
    else {
      val departmentData = departments.filter(x => x._2.projectId.contains(projectId))
      if(departmentData.isEmpty) Option(List.empty[Employee])
      else Option(employees.filter(x => x.departmentId == departmentData.head._1))
    }
  }

}
