package models.database

import play.api.db.slick.Config.driver.simple._
import models.Hotel

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
private[models] object Hotels extends Table[Hotel]("hotel") {

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

  def * = id.? ~ name ~ city.? ~ state.? ~ countryCode.? ~ addr1.? ~ addr2.? ~ zipCode.?  ~ latitude.?  ~ longitude.? <>(Hotel.apply _, Hotel.unapply _)

  def autoInc = id.? ~ name ~ city.? ~ state.? ~ countryCode.? ~ addr1.? ~ addr2.? ~ zipCode.?  ~ latitude.?  ~ longitude.? <>(Hotel.apply _, Hotel.unapply _) returning id


  val byId = createFinderBy(_.id)
  val byName = createFinderBy(_.name)
  val byCity = createFinderBy(_.city)
  val byState = createFinderBy(_.state)
  val byCountryCode = createFinderBy(_.countryCode)

}