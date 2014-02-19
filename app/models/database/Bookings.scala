package models.database

import play.api.db.slick.Config.driver.simple._
import com.github.tototoshi.slick.JodaSupport._
import org.joda.time.DateTime
import models.Booking

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
private[models] object Bookings extends Table[Booking]("booking") {

  def bkgnum = column[String]("bkgnum")

  def custid = column[Long]("custid")

  def hotelid = column[Long]("hotelid")

  def roomid = column[Long]("roomid")

  def bkgstatus = column[String]("bkgstatus")

  def fromDate = column[DateTime]("fromdate")

  def toDate = column[DateTime]("todate")

  def createDate = column[DateTime]("createDate")

  def custloginid = column[String]("custloginid")

  def paxid = column[Long]("paxid")

  def userid = column[String]("userid")

  def ccnum = column[Option[String]]("ccnum")

  def * = bkgnum ~ hotelid ~ custid ~ roomid ~ bkgstatus ~ fromDate ~ toDate ~ createDate ~ paxid ~ userid ~ custloginid ~ ccnum <>(Booking.apply _, Booking.unapply _)


  val byCustId = createFinderBy(_.custid)
  val byBkgNum = createFinderBy(_.bkgnum)
}