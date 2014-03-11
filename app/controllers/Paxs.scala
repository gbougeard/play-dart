package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json

import models.Pax
import models.Paxs._

import play.api.db.slick._
import play.api.Play.current
import play.Logger

object Paxs extends Controller {


  /**
   * Describe the pax form (used in both edit and create screens).
   */
//  val paxForm = Form(
//    mapping(
//      "id" -> optional(longNumber),
//      "name" -> nonEmptyText,
//      "city" -> optional(text),
//      "state" -> optional(text),
//      "countryCode" -> optional(text),
//      "addr1" -> optional(text),
//      "addr2" -> optional(text),
//      "zipCode" -> optional(text),
//      "latitude" -> optional(bigDecimal),
//      "longitude" -> optional(bigDecimal)
//    )
//      (Pax.apply)(Pax.unapply)
//  )

  val Home = Redirect(routes.Paxs.list())

  // -- Actions
  def index = Action {
    Home
  }

  def list(page: Int =0, orderBy: Int=0) = DBAction {
    implicit request =>
      val paxs = models.Paxs.findPage(page, orderBy)
      render {
        case Accepts.Html() => Ok(views.html.paxs.list("List paxs", paxs, orderBy))
        case Accepts.Json() => Ok(Json.toJson(paxs))
      }
  }

  def view(id: Long) = DBAction {
    implicit request =>
      models.Paxs.findById(id).map {
        pax => {
          render {
            case Accepts.Html() => Ok(views.html.paxs.view("View Pax", pax))
            case Accepts.Json() => Ok(Json.toJson(pax))
          }
        }
      } getOrElse {
        Logger.warn(s"Pax $id not found")
        NotFound
      }
  }

//  def edit(id: Long) = DBAction {
//    implicit request =>
//      models.Paxs.findById(id).map {
//        pax => {
//          val html = views.html.paxs.edit("Edit Pax", id, paxForm.fill(pax))
//          Ok(html)
//        }
//      } getOrElse {
//        Logger.warn(s"Pax $id not found")
//        NotFound
//      }
//  }
//
//  /**
//   * Handle the 'edit form' submission
//   *
//   * @param id Id of the computer to edit
//   */
//  def update(id: Long) = DBAction {
//    implicit request =>
//      paxForm.bindFromRequest.fold(
//        formWithErrors => BadRequest(views.html.paxs.edit("Edit Pax - errors", id, formWithErrors)),
//        pax => {
//          models.Paxs.update(id, pax)
//          Redirect(routes.Paxs.edit(id)).flashing("success" -> "Pax %s has been updated".format(pax.name))
//          //          Redirect(routes.Paxs.view(pax.id))
//        }
//      )
//  }
//
//  /**
//   * Display the 'new computer form'.
//   */
//  def create = Action {
//    implicit request =>
//      Ok(views.html.paxs.create("New Pax", paxForm))
//  }
//
//  /**
//   * Handle the 'new computer form' submission.
//   */
//  def save = DBAction(parse.json) {
//    implicit request =>
//      val json = request.body
//      //      println(json)
//      val pax = json.as[Pax]
//      val id = models.Paxs.insert(pax)
//      Ok(Json.toJson(id))
//    //        Home.flashing("success" -> "Pax %s has been created".format(pax.name))
//  }
//
//  /**
//   * Handle computer deletion.
//   */
//  def delete(id: Long) = DBAction {
//    implicit request =>
//      models.Paxs.delete(id)
//      Home.flashing("success" -> "Pax has been deleted")
//  }
//
//  def setAvail() = DBAction(parse.json){
//    implicit request =>
//      val json = request.body
//      //      println(json)
//      val pax = json.as[Pax]
//      val id = models.Paxs.insert(pax)
//      Ok(Json.toJson(id))
//  }

}