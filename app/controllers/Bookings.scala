package controllers

import play.api.mvc._

import models.{BookingFilter, Booking}
import models.Bookings._

import play.api.libs.json.Json

import play.api.db.slick._
import play.api.Play.current
import play.Logger


object Bookings extends Controller {


  val Home = Redirect(routes.Bookings.list())

  // -- Actions
  def index = Action {
    Home
  }

  def list(page: Int = 0, orderBy: Int = 0) = DBAction {
    implicit request =>
      val custId: Option[Long] = request.getQueryString("custid").map {
        _.toLong
      }
      val hotelId: Option[Long] = request.getQueryString("hotelid").map {
        _.toLong
      }
      val statuses:Set[String] = request.getQueryString("statuses").map {
        s => s.split(',').toSet
      } getOrElse Set()

      val bookingFilter = new BookingFilter(custId, hotelId, statuses)

      val bookings = models.Bookings.findPage(page, orderBy, bookingFilter)

      render {
        case Accepts.Html() => Ok(views.html.bookings.list("List bookings", bookings, orderBy))
        case Accepts.Json() => Ok(Json.toJson(bookings))
      }
  }

  def view(id: String) = DBAction {
    implicit request =>
      models.Bookings.findByBkgNum(id).map {
        booking => {
          render {
            case Accepts.Html() => Ok(views.html.bookings.view("View booking", booking))
            case Accepts.Json() => Ok(Json.toJson(booking))
          }
        }
      } getOrElse {
        Logger.warn(s"Booking $id not found")
        NotFound
      }
  }


}

