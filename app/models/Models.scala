package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Writes
import scala.slick.lifted.TableQuery
import models.database._

// Use the implicit threadLocalSession


/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

object Page {
  implicit def writes[A : Writes]: Writes[Page[A]] = (
    (__ \ 'items).write[Seq[A]] ~
      (__ \ 'page).write[Int] ~
      (__ \ 'offset).write[Long] ~
      (__ \ 'total).write[Long]
    ) (unlift(Page.unapply[A]))

}

private[models] trait DAO {
  val hotels = TableQuery[Hotels]
  val bookings = TableQuery[Bookings]
  val userOpinions = TableQuery[UserOpinions]
  val hotelCatRooms = TableQuery[HotelCatRooms]
  val paxs = TableQuery[Paxs]
}





