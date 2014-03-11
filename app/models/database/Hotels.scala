package models.database

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.Hotel

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
class Hotels(tag: Tag) extends Table[Hotel](tag, "hotel") {

  def id = column[Long]("hotelid", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def city = column[String]("city")

  def countryCode = column[String]("country")

  def state = column[String]("state")

  def addr1 = column[String]("addr1")

  def addr2 = column[String]("addr2")

  def zipCode = column[String]("zip")

  def latitude = column[BigDecimal]("latitude")

  def longitude = column[BigDecimal]("longitude")

  def * = (id.? , name, city.? , state.?, countryCode.?, addr1.?, addr2.?, zipCode.?, latitude.?, longitude.?) <>(Hotel.tupled, Hotel.unapply _)

  // A reified foreign key relation that can be navigated to create a join
//  def supplier = foreignKey("SUP_FK", supID, suppliers)(_.id)

}