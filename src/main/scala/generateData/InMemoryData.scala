package generateData

import models._

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.UUID
import scala.annotation.tailrec
import scala.util.Random

object InMemoryData {

  //list of department names
  val departmentNames = List[String]("Management","Marketing","Sales","Support","Quality Assurance","Design","Development","Human Resource","Finance","Consultant")
  //states name list
  val statesName = List[String]("Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal")

  //alpha only string to generate random string
  val alpha = "abcdefghijklmnopqrstuvwxyz"

  val projects = List.range(1001, 1011).map(x => (x,Project(x, "Project-" + x))).toMap
  val departments = List.range(1,11).map(x => (x,Department(x,departmentNames(x-1),1000+x))).toMap

  val employees = List.range(0,101).map(x=> Employee(
    UUID.randomUUID(),
    randomName(6).capitalize,
    generateDate(x),
    2000*(20+Random.nextInt(26)),
    1+Random.nextInt(10),
    Address(1000+Random.nextInt(1001),s"Area-$x",Option(statesName(0+Random.nextInt(28))),110000+(1000+Random.nextInt(1001))),
    s"+91 ${1000+Random.nextInt(9000)}-${10000+Random.nextInt(90000)}")
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
}
