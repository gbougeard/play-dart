package models

import play.api.db.slick.Config.driver.simple._

import play.api.libs.json._


case class Hotel(
                  id: Option[Long],
                  name: String,
                  city: Option[String],
                  state: Option[String] = None,
                  countryCode: Option[String],
                  addr1: Option[String] = None,
                  addr2: Option[String] = None,
                  zipCode: Option[String] = None,
                  latitude: Option[BigDecimal] = None,
                  longitude: Option[BigDecimal] = None
                  )

// define tables
object Hotels extends DAO {
  lazy val pageSize = 10


  def findAll(implicit session: Session): Seq[Hotel] = {
    val q = (for (c <- Hotels.sortBy(_.name)) yield c)
    //          .drop(20000)
    q.take(10000).list
  }

  def count(implicit session: Session): Int = {
    Query(Hotels.length).first
  }

  def findPage(page: Int = 0, orderField: Int)(implicit session: Session): Page[Hotel] = {

    val offset = pageSize * page
    val hotels = (
      for {c <- Hotels
        .sortBy(hotel => orderField match {
        case 1 => hotel.name.asc
        case -1 => hotel.name.desc
        case 2 => hotel.city.asc
        case -2 => hotel.city.desc
        case 3 => hotel.countryCode.asc
        case -3 => hotel.countryCode.desc
      })
        .drop(offset)
        .take(pageSize)
      } yield c)


    Page(hotels.list, page, offset, count)
  }

  def findById(id: Long)(implicit session: Session): Option[Hotel] = {
    Hotels.where(_.id === id).firstOption
  }

  def findByCity(cityName: String)(implicit session: Session): Seq[Hotel] = {
    Hotels.where(_.city === cityName).list
  }

  def findByState(stateName: String)(implicit session: Session): Seq[Hotel] = {
    Hotels.where(_.state === stateName).list
  }

  def findByCountryCode(countryName: String)(implicit session: Session): Seq[Hotel] = {
    Hotels.where(_.countryCode === countryName).list
  }


  def insert(hotel: Hotel)(implicit session: Session): Long = {
    Hotels.insert(hotel)
  }

  def update(hotelId: Long, hotel: Hotel)(implicit session: Session) = {
    Hotels.where(_.id === hotelId).update(hotel)
  }

  def delete(hotelId: Long)(implicit session: Session) = {
    Hotels.where(_.id === hotelId).delete
  }


  //JSON
  implicit val hotelFormat = Json.format[Hotel]

}

