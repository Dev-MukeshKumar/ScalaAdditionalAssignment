package operations

import models._

import scala.collection.immutable.ListMap

object Operation2 {
  def getAverageSalaryInDepartments(employees: List[Employee], departments:Map[Int,Department]):Unit = {
    val departmentWiseList = departments.map(department =>(department._1,employees.filter(employee => employee.departmentId == department._2.id)))
    val averageSalaryList = departmentWiseList.map(x => (x._1,((x._2.map(employee => employee.salary).sum) / x._2.length.toDouble)))
    println("----Average salary of each department----")
    ListMap(averageSalaryList.toSeq.sortBy(_._1):_*).foreach(data => println(s"Department ${data._1} - ${data._2.formatted("%.2f")}"))
  }
}
