package controllers

import play.api.mvc._

import models.{BookingFilter, Booking}
import models.Booking._
import play.api.libs.json.Json


object Bookings extends Controller {


  val Home = Redirect(routes.Bookings.list(0, 0))

  // -- Actions
  def index = Action {
    Home
  }

  def list(page: Int, orderBy: Int) = Action {
    implicit request =>
      val custId: Option[Long] = request.getQueryString("custid").map {
        _.toLong
      }
      val hotelId: Option[Long] = request.getQueryString("hotelid").map {
        _.toLong
      }
      val statuses:Set[String] = request.getQueryString("statuses").map {
        s => s.split(',').toSet
      } getOrElse (Set())

      val bookingFilter = new BookingFilter(custId, hotelId, statuses)

      val bookings = Booking.findPage(page, orderBy, bookingFilter)

      render {
        case Accepts.Html() => Ok(views.html.bookings.list("List bookings", bookings, orderBy))
        case Accepts.Json() => Ok(Json.toJson(bookings))
      }
  }

  def view(id: String) = Action {
    implicit request =>
      Booking.findByBkgNum(id).map {
        booking => {
          render {
            case Accepts.Html() => Ok(views.html.bookings.view("View booking", booking))
            case Accepts.Json() => Ok(Json.toJson(booking))
          }
        }
      } getOrElse (NotFound)
  }


}

