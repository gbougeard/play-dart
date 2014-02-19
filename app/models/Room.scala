package models

import play.api.Play.current

import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import play.api.libs.json._
import models.database.HotelCatRooms


case class HotelCatRoom(hotelid: Long,
                        custid: Long,
                        catcode: Long,
                        roomtcode: Long
                         )

// define tables
object HotelCatRoom {

  lazy val pageSize = 10
  lazy val totalRows = count

  def findAll: Seq[HotelCatRoom] = {
    DB.withSession {
      implicit session: Session =>
        (for (c <- HotelCatRooms) yield c).list
    }
  }

  def count: Int = {
    DB.withSession {
      implicit session: Session =>
        Query(HotelCatRooms.length).first
    }
  }


  def findByHotelId(id: Long): Seq[HotelCatRoom] = {
    DB.withSession {
      implicit session: Session =>
        HotelCatRooms.byHotelId(id).list
    }
  }


  //JSON
  implicit val hotelFormat = Json.format[HotelCatRoom]

}

