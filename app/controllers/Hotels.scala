package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json

import models.Hotel
import models.Page._
import models.Hotel._

import play.api.libs.iteratee._

import play.Logger

object Hotels extends Controller {


  /**
   * Describe the hotel form (used in both edit and create screens).
   */
  val hotelForm = Form(
    mapping(
      "id" -> optional(longNumber),
      "name" -> nonEmptyText,
      "city" -> optional(text),
      "state" -> optional(text),
      "countryCode" -> optional(text),
      "addr1" -> optional(text),
      "addr2" -> optional(text),
      "zipCode" -> optional(text),
      "latitude" -> optional(bigDecimal),
      "longitude" -> optional(bigDecimal)
    )
      (Hotel.apply)(Hotel.unapply)
  )

  val Home = Redirect(routes.Hotels.list(0, 0))

  // -- Actions
  def index = Action {
    Home
  }

  def list(page: Int, orderBy: Int) = Action {
    implicit request =>
      val hotels = models.Hotel.findPage(page, orderBy)
      val html = views.html.hotels.list("List hotels", hotels, orderBy)
      Ok(html)
  }

  def view(id: Long) = Action {
    implicit request =>
//      graphvizActor ! 0
      models.Hotel.findById(id).map {
        hotel => {
          val html = views.html.hotels.view("View Hotel", hotel)
          Ok(html)
        }
      } getOrElse (NotFound)
  }

  def edit(id: Long) = Action {
    implicit request =>
      models.Hotel.findById(id).map {
        hotel => {
          val html = views.html.hotels.edit("Edit Hotel", id, hotelForm.fill(hotel))
          Ok(html)
        }
      } getOrElse (NotFound)
  }

  /**
   * Handle the 'edit form' submission
   *
   * @param id Id of the computer to edit
   */
  def update(id: Long) = Action {
    implicit request =>
      hotelForm.bindFromRequest.fold(
        formWithErrors => BadRequest(views.html.hotels.edit("Edit Hotel - errors", id, formWithErrors)),
        hotel => {
          models.Hotel.update(id, hotel)
          Redirect(routes.Hotels.edit(id)).flashing("success" -> "Hotel %s has been updated".format(hotel.name))
          //          Redirect(routes.Hotels.view(hotel.id))
        }
      )
  }

  /**
   * Display the 'new computer form'.
   */
  def create = Action {
    implicit request =>
      Ok(views.html.hotels.create("New Hotel", hotelForm))
  }

  /**
   * Handle the 'new computer form' submission.
   */
  def save = Action(parse.json) {
    implicit request =>
      val json = request.body
      //      println(json)
      val hotel = json.as[Hotel]
      val id = models.Hotel.insert(hotel)
      Ok(Json.toJson(id))
    //        Home.flashing("success" -> "Hotel %s has been created".format(hotel.name))
  }

  /**
   * Handle computer deletion.
   */
  def delete(id: Long) = Action {
    implicit request =>
      models.Hotel.delete(id)
      Home.flashing("success" -> "Hotel has been deleted")
  }




  def setAvail() = Action(parse.json){
    implicit request =>
      val json = request.body
      //      println(json)
      val hotel = json.as[Hotel]
      val id = models.Hotel.insert(hotel)
      Ok(Json.toJson(id))
  }



}