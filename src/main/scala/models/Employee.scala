package models

import java.util.UUID

case class Employee(id:UUID,name:String,doj:Option[String],salary:Int,departmentId:Int,address:Address,phoneNumber: String){
}
