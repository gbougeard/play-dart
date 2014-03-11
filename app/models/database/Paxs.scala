package models.database

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.Pax

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
class Paxs(tag: Tag) extends Table[Pax](tag, "pax") {

  def id = column[Long]("paxid", O.PrimaryKey, O.AutoInc)

  def custId = column[Long]("custid", O.PrimaryKey)

  def partId = column[Long]("partid", O.PrimaryKey)

  def login = column[String]("login")

  def city = column[String]("city")

  def countryCode = column[String]("ctrycode")

  def state = column[String]("state")

  def addr1 = column[String]("addr1")

  def addr2 = column[String]("addr2")

  def zipCode = column[String]("zip")

  def email = column[String]("email")

  def firstName = column[String]("firstname")

  def lastName = column[String]("lastname")

  def * = (id.?, custId, partId, login.?, city.?, state.?, countryCode.?, addr1.?, addr2.?, zipCode.?, email, firstName.?, lastName.?) <>(Pax.tupled, Pax.unapply _)

}