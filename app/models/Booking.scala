package models

import play.api.Play.current

import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import play.api.libs.json._
import org.joda.time.DateTime
import models.database.Bookings


case class Booking(
                    bkgnum: String,
                    hotelid: Long,
                    custid: Long,
                    roomid: Long,
                    bkgstatus: String,
                    fromDate: DateTime,
                    toDate: DateTime,
                    createDate: DateTime,
                    paxid: Long,
                    userid: String,
                    custloginid: String,
                    ccnum: Option[String]
                    )

case class BookingFilter(custId: Option[Long],
                         hotelId: Option[Long],
                         statuses: Set[String])

object Booking {
  lazy val pageSize = 10
  lazy val totalRows = count

  def findAll: Seq[Booking] = {
    DB.withSession {
      implicit session:Session =>
        (for (c <- Bookings) yield c).list
    }
  }

  def count: Int = {
    DB.withSession {
      implicit session:Session =>
        Query(Bookings.length).first
    }
  }

  def findPage(page: Int = 0, orderField: Int, filter: BookingFilter): Page[Booking] = {

    val offset = pageSize * page

    DB.withSession {
      implicit session:Session =>

        val q = Query(Bookings)

        val bookings = q
          .filter(b => filter.custId match {
          case Some(id) => b.custid === id
          case None => b.custid === b.custid
        }).filter(b => filter.hotelId match {
          case Some(id) => b.hotelid === id
          case None => b.hotelid === b.hotelid
        }).filter(b => filter.statuses.isEmpty match {
          case false => b.bkgstatus.inSet(filter.statuses)
          case true => b.bkgstatus === b.bkgstatus
        })
          .sortBy(booking => orderField match {
          case 1 => booking.bkgnum.asc
          case -1 => booking.bkgnum.desc
          case 2 => booking.custid.asc
          case -2 => booking.custid.desc
          case 3 => booking.bkgstatus.asc
          case -3 => booking.bkgstatus.desc
          case 4 => booking.fromDate.asc
          case -4 => booking.fromDate.desc
          case 5 => booking.toDate.asc
          case -5 => booking.toDate.desc
          case 6 => booking.createDate.asc
          case -6 => booking.createDate.desc
          case 9 => booking.createDate.asc
          case -9 => booking.createDate.desc
        })
          .drop(offset)
          .take(pageSize)

        Page(bookings.list, page, offset, count)
    }
  }


  def findByCustId(id: Long): Seq[Booking] = {
    DB.withSession {
      implicit session:Session =>
        Bookings.byCustId(id).list
    }
  }

  def findByBkgNum(id: String): Option[Booking] = {
    DB.withSession {
      implicit session:Session =>
        Bookings.byBkgNum(id).firstOption()
    }
  }


  //JSON
  implicit val hotelFormat = Json.format[Booking]

}

