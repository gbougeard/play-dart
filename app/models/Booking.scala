package models


import play.api.db.slick.Config.driver.simple._

import play.api.libs.json._
import org.joda.time.DateTime


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
                    ccnum: Option[String] = None
                    )

case class BookingFilter(custId: Option[Long],
                         hotelId: Option[Long],
                         statuses: Set[String])

object Bookings extends DAO {
  lazy val pageSize = 10

  def findAll(implicit session: Session): Seq[Booking] = {
    bookings.list
  }

  def count(implicit session: Session): Int = {
    bookings.length.run
  }

  def findPage(page: Int = 0, orderField: Int, filter: BookingFilter)(implicit session: Session): Page[Booking] = {

    val offset = pageSize * page

    val bookings = bookings
      .filter(b => filter.custId match {
      case Some(id) => b.custid === id
      case None => b.custid === b.custid
    })
      .filter(b => filter.hotelId match {
      case Some(id) => b.hotelid === id
      case None => b.hotelid === b.hotelid
    })
      .filter(b => filter.statuses.isEmpty match {
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

  def findByCustId(id: Long)(implicit session: Session): Seq[Booking] = {
    bookings.where(_.custid === id).list
  }

  def findByBkgNum(id: String)(implicit session: Session): Option[Booking] = {
    bookings.where(_.bkgnum === id).firstOption()
  }


  //JSON
  implicit val hotelFormat = Json.format[Booking]

}

