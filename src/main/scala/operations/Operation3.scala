package operations

import models._

object Operation3 {
  def deleteProject(projectId:Int,departments:Map[Int,Department],projects:Map[Int,Project]): (Map[Int,Department],Map[Int,Project])= {
    if(!projects.contains(projectId)) {
      println(s"Project id: ${projectId} does not exists!")
      (departments,projects)
    }
    else {
      println(s"Project with id:${projectId} deleted! and update in the respective department!")
      (departments.map(x =>
        if(x._2.projectId.contains(projectId))
          (x._1,Department(x._1,x._2.name,None))
        else x
      ),
      projects.filter(x => x._1 != projectId))
    }

  }
}
