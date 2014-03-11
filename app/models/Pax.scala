package models

import play.api.db.slick.Config.driver.simple._

import play.api.libs.json._


case class Pax(
                  id: Option[Long],
                  custId: Long,
                  partId: Long,
                  login: Option[String],
                  city: Option[String],
                  state: Option[String] = None,
                  countryCode: Option[String],
                  addr1: Option[String] = None,
                  addr2: Option[String] = None,
                  zipCode: Option[String] = None,
                  email:String,
                  firstName: Option[String] = None,
                  lastName: Option[String] = None
                  )


// define tables
object Paxs extends DAO {
  lazy val pageSize = 10


  def findAll(implicit session: Session): Seq[Pax] = {
    val q = paxs.sortBy(_.lastName)
    q.list
  }

  def count(implicit session: Session): Int = {
    paxs.length.run
  }

  def findPage(page: Int = 0, orderField: Int)(implicit session: Session): Page[Pax] = {

    val offset = pageSize * page
    val q = for {
      c <- paxs.sortBy(
        pax =>
          orderField match {
            case 1 => pax.email.asc
            case -1 => pax.email.desc
            case 2 => pax.custId.asc
            case -2 => pax.custId.desc
            case 3 => pax.partId.asc
            case -3 => pax.partId.desc
            case _ => pax.login.asc
          }
      )
        .drop(offset)
        .take(pageSize)
    } yield c


    Page(q.list, page, offset, count)
  }

  def findById(id: Long)(implicit session: Session): Option[Pax] = {
    paxs.where(_.id === id).firstOption
  }

  def findByCity(cityName: String)(implicit session: Session): Seq[Pax] = {
    paxs.where(_.city === cityName).list
  }

  def findByState(stateName: String)(implicit session: Session): Seq[Pax] = {
    paxs.where(_.state === stateName).list
  }

  def findByCountryCode(countryName: String)(implicit session: Session): Seq[Pax] = {
    paxs.where(_.countryCode === countryName).list
  }

  def insert(pax: Pax)(implicit session: Session): Long = {
    paxs.insert(pax)
  }

  def update(paxId: Long, pax: Pax)(implicit session: Session) = {
    paxs.where(_.id === paxId).update(pax)
  }

  def delete(paxId: Long)(implicit session: Session) = {
    paxs.where(_.id === paxId).delete
  }

  //JSON
  implicit val paxFormat = Json.format[Pax]

}



