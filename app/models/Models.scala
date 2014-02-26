package models

import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Writes
import scala.slick.lifted.TableQuery
import models.database.{HotelCatRooms, UserOpinions, Bookings, Hotels}

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
  val Hotels = TableQuery[Hotels]
  val Bookings = TableQuery[Bookings]
  val UserOpinions = TableQuery[UserOpinions]
  val HotelCatRooms = TableQuery[HotelCatRooms]
}





