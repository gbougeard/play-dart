package models.database

import play.api.db.slick.Config.driver.simple._
import models.UserOpinion

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
private[models] object UserOpinions extends Table[UserOpinion]("useropinion") {

  def id = column[String]("bkgnum", O.PrimaryKey)

  def hotelid = column[Long]("hotelid")

  def custid = column[Long]("custid")

  def note = column[Double]("globalnote")

  def * = id.? ~ hotelid ~ custid ~ note <>(UserOpinion.apply _, UserOpinion.unapply _)

//  def autoInc = id.? ~ name ~ city.? ~ state.? ~ countryCode.? ~ addr1.? ~ addr2.? ~ zipCode.?  ~ latitude.?  ~ longitude.? <>(UserOpinion.apply _, UserOpinion.unapply _) returning id


  val byId = createFinderBy(_.id)
  val byHotelid = createFinderBy(_.hotelid)
  val byCustid = createFinderBy(_.custid)

}