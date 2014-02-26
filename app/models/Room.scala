package models

import play.api.db.slick.Config.driver.simple._
import play.api.libs.json._

case class HotelCatRoom(hotelid: Long,
                        custid: Long,
                        catcode: Long,
                        roomtcode: Long
                         )

// define tables
object HotelCatRooms extends DAO {

  lazy val pageSize = 10

  def findAll(implicit session: Session): Seq[HotelCatRoom] = {
    (for (c <- HotelCatRooms) yield c).list
  }

  def count(implicit session: Session): Int = {
    Query(HotelCatRooms.length).first
  }


  def findByHotelId(id: Long)(implicit session: Session): Seq[HotelCatRoom] = {
    HotelCatRooms.where(_.hotelid === id).list
  }


  //JSON
  implicit val hotelFormat = Json.format[HotelCatRoom]

}

