package models

import java.time.LocalDate
import java.util.UUID

case class Employee(id:UUID, name:String, doj:Option[LocalDate], salary:Int, departmentId:Int, address:Address, phoneNumber: String){
}
