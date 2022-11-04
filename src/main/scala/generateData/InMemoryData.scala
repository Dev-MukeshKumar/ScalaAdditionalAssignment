package generateData

import models._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import scala.annotation.tailrec
import scala.util.Random

object InMemoryData {

  //list of department names
  val departmentNames: List[String] = List[String]("Management","Marketing","Sales","Support","Quality Assurance","Design","Development","Human Resource","Finance","Consultant")

  //states name list
  val statesName: List[String] = List[String]("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal")

  //alpha only string to generate random string
  val alpha = "abcdefghijklmnopqrstuvwxyz"

  val projects: Map[Int,Project] = List.range(1001, 1011).map(x => (x,Project(x, randomName(7)))).toMap

  val departments: Map[Int,Department] = mapProjects(List.range(1,11).map(x => (x,Department(x,departmentNames(x-1),Option(0)))).toMap)

  val employees: List[Employee] = List.range(0,101).map(x=> Employee(
    UUID.randomUUID(),
    randomName(6).capitalize,
    generateDate(x),
    2000*(20+Random.nextInt(26)),
    1+Random.nextInt(10),
    Address(1000+Random.nextInt(1001),s"Area-$x",Option(statesName(0+Random.nextInt(28))),110000+(1000+Random.nextInt(1001))),
    s"+91 ${1000+Random.nextInt(9000)}-${100000+Random.nextInt(1000000)}")
  )

  //helper methods in generating data
  def generateDate(x:Int): Option[LocalDate] ={
    val date = s"01/01/201${if((x/10)!=10) (x/10) else 9}"
    val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
    Option(LocalDate.parse(date,formatter))
  }

  @tailrec
  def randomName(n:Int,acc:String=""): String ={
    if(n<=1) acc
    else randomName(n-1,acc + "" +alpha.charAt(0+Random.nextInt(26)))
  }

  @tailrec
  def mapProjects(departments:Map[Int,Department],n:Int=0):Map[Int,Department] = {
    val randomProjectId = Option(projects.map(_._2.id).toList(0+Random.nextInt(10)))
    if(n >= departments.size) departments
    else if(!departments.map(_._2.projectId).toList.contains(randomProjectId)) {
      departments.find(_._2.projectId.contains(0)) match {
        case Some((mapId,_)) =>
        mapProjects(
          departments.map(
          department => department._2 match {
            case Department(eId,eName,eProjectId) if eId == mapId => (mapId,Department(eId,eName,randomProjectId))
            case Department(eId,eName,eProjectId) => (eId,Department(eId,eName,eProjectId))
          }
        ), n+1)
      }
    }
    else mapProjects(departments,n)
  }
}
