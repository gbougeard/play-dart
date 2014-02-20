package models

import play.api.Play.current

import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import play.api.libs.json._
import models.database.UserOpinions


case class UserOpinion(
                        id: Option[String],
                        hotelid: Long,
                        custid: Long,
                        note: Double
                        )

// define tables
object UserOpinion {
  lazy val pageSize = 10
  lazy val totalRows = count

  def findByHotelidAndCustid(hid: Long, cid: Long): Seq[UserOpinion] = {
    DB.withSession {
      implicit session: Session =>
        (for {
          c <- UserOpinions
          if (c.hotelid === hid)
          if (c.custid === cid)
        } yield c)
          .list
    }
  }

  def count: Int = {
    DB.withSession {
      implicit session: Session =>
        Query(UserOpinions.length).first
    }
  }


  //JSON
  implicit val opinionFormat = Json.format[UserOpinion]

}





