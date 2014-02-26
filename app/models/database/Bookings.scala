package models.database

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import com.github.tototoshi.slick.MySQLJodaSupport._
import org.joda.time.DateTime
import models.Booking

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
class Bookings(tag:Tag) extends Table[Booking](tag, "booking") {

  def bkgnum = column[String]("bkgnum")

  def custid = column[Long]("custid")

  def hotelid = column[Long]("hotelid")

  def roomid = column[Long]("roomid")

  def bkgstatus = column[String]("bkgstatus")

  def fromDate = column[DateTime]("fromdate")

  def toDate = column[DateTime]("todate")

  def createDate = column[DateTime]("createDate")

  def paxid = column[Long]("paxid")

  def userid = column[String]("userid")

  def custloginid = column[String]("custloginid")

  def ccnum = column[String]("ccnum")

  def * = (bkgnum , hotelid , custid , roomid , bkgstatus , fromDate , toDate , createDate , paxid , userid , custloginid , ccnum.?) <>(Booking.tupled, Booking.unapply _ )

}