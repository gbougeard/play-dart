package models


import play.api.db.slick.Config.driver.simple._

import play.api.libs.json._


case class UserOpinion(
                        id: Option[String] = None,
                        hotelid: Long,
                        custid: Long,
                        note: Double,
                        welcome: Int,
                        atmosphere: Int,
                        comfort: Int
                        )

// define tables
object UserOpinions extends DAO {
  lazy val pageSize = 10

  def findByHotelidAndCustid(hid: Long, cid: Long)(implicit session: Session): Seq[UserOpinion] = {
    (for {
      c <- UserOpinions
      if (c.hotelid === hid)
      if (c.custid === cid)
    } yield c)
      .list
  }

  def count(implicit session: Session): Int = {
    Query(UserOpinions.length).first
  }


  //JSON
  implicit val opinionFormat = Json.format[UserOpinion]

}





