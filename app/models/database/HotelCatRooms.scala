package models.database

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.HotelCatRoom

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
class HotelCatRooms(tag: Tag) extends Table[HotelCatRoom](tag, "hotelcatroom") {

  def hotelid = column[Long]("hotelid")

  def custid = column[Long]("custid")

  def catcode = column[Long]("catcode")

  def roomtcode = column[Long]("roomtcode")

  def * = (hotelid, custid, catcode, roomtcode) <> (HotelCatRoom.tupled, HotelCatRoom.unapply _)

}