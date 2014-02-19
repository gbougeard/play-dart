package models.database

import play.api.db.slick.Config.driver.simple._
import models.HotelCatRoom

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
private[models] object HotelCatRooms extends Table[HotelCatRoom]("hotelcatroom") {

  def hotelid = column[Long]("hotelid")
  def custid = column[Long]("custid")
  def catcode= column[Long]("catcode")
  def roomtcode = column[Long]("roomtcode")

  def * = hotelid ~ custid ~ catcode ~ roomtcode <>(HotelCatRoom.apply _, HotelCatRoom.unapply _)

  val byHotelId = createFinderBy(_.hotelid)
}