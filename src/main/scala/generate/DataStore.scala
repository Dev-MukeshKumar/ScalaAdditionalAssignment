package generate

import models._

import java.util.UUID
import scala.util.Random

object DataStore {
  //list
  val projects = List.range(1001, 1011).map(x => (x,Project(x, "Project-" + x))).toMap
  val departments = List.range(1,11).map(x => (x,Department(x,"Department-"+x,1000+x))).toMap

  val employees = List.range(1,11).map(x=> Employee(
    UUID.randomUUID(),
    "Employee-"+x,
    Option(s"01/01/201${if(x/10.toInt!=10) x/10.toInt else 9}"),
    2000*(20+Random.nextInt(26)),
    1+Random.nextInt(10),
    Address(1000+Random.nextInt(1001),s"Area-$x",Option("asd"),110000+(1000+Random.nextInt(1001))),
    s"+91 ${1000+Random.nextInt(9000)}-${10000+Random.nextInt(90000)}")
  )
}
