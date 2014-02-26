package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import play.api.db.slick._
import play.api.Play.current

import models.UserOpinions._

object UserOpinions extends Controller {

  def byHotelidAndCustid(hid:Long, cid:Long) = DBAction {
    implicit request =>
    val opinions = models.UserOpinions.findByHotelidAndCustid(hid, cid)
    Ok(Json.toJson(opinions))
  }

}