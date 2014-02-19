package models


import play.api.Play.current

import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB

import play.api.libs.json._
import models.database.Hotels


case class Hotel(
                  id: Option[Long],
                  name: String,
                  city: Option[String],
                  state: Option[String] = None,
                  countryCode: Option[String],
                  addr1: Option[String] = None,
                  addr2: Option[String] = None,
                  zipCode: Option[String] =None,
                  latitude: Option[BigDecimal] =None,
                  longitude: Option[BigDecimal] =None
                  )

// define tables
object Hotel {
  lazy val pageSize = 10
  lazy val totalRows = count

  def findAll: Seq[Hotel] = {
    DB.withSession {
      implicit session: Session =>
        (for (c <- Hotels.sortBy(_.name)) yield c)
//          .drop(20000)
          .take(10000)
          .list
    }
  }

  def count: Int = {
    DB.withSession {
      implicit session: Session =>
        Query(Hotels.length).first
    }
  }

  def findPage(page: Int = 0, orderField: Int): Page[Hotel] = {

    val offset = pageSize * page
    DB.withSession {
      implicit session: Session =>
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
          } yield c).list


        Page(hotels, page, offset, totalRows)
    }
  }

  def findById(id: Long): Option[Hotel] = {
    DB.withSession {
      implicit session: Session =>
        Hotels.byId(id).firstOption
    }
  }

  def findByCity(cityName: String): Seq[Hotel] = {
    DB.withSession {
      implicit session: Session =>
        Hotels.byCity(cityName).list
    }
  }

  def findByState(countryName: String): Seq[Hotel] = {
    DB.withSession {
      implicit session: Session =>
        Hotels.byState(countryName).list
    }
  }

  def findByCountryCode(countryName: String): Seq[Hotel] = {
    DB.withSession {
      implicit session: Session =>
        Hotels.byCountryCode(countryName).list
    }
  }


  def insert(hotel: Hotel): Long = {
    DB.withSession {
      implicit session: Session =>
        Hotels.autoInc.insert(hotel)
    }
  }

  def update(id: Long, hotel: Hotel) = {
    DB.withSession {
      implicit session: Session => {
        val hotel2update = hotel.copy(Some(id))
        Hotels.where(_.id === id).update(hotel2update)
      }
    }
  }

  def delete(hotelId: Long) = {
    DB.withSession {
      implicit session: Session =>
        Hotels.where(_.id === hotelId).delete
    }
  }


  //JSON
  implicit val hotelFormat = Json.format[Hotel]

}

