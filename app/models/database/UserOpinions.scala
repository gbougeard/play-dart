package models.database

import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.Tag
import models.UserOpinion

/**
 * Created by gbougeard on 12/9/13.
 */
// define tables
class UserOpinions(tag: Tag) extends Table[UserOpinion](tag, "useropinion") {

  def id = column[String]("bkgnum", O.PrimaryKey)

  def hotelid = column[Long]("hotelid")

  def custid = column[Long]("custid")

  def note = column[Double]("globalnote")

  def welcome = column[Int]("welcome")

  def atmosphere = column[Int]("atmosphere")

  def comfort = column[Int]("comfort")

  def * = (id.?, hotelid, custid, note, welcome, atmosphere, comfort) <>(UserOpinion.tupled, UserOpinion.unapply _)

  //  def autoInc = id.? , name , city.? , state.? , countryCode.? , addr1.? , addr2.? , zipCode.?  , latitude.?  , longitude.? <>(UserOpinion.apply _, UserOpinion.unapply _) returning id


}