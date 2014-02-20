package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object UserOpinions extends Controller {

  def byHotelidAndCustid(hid:Long, cid:Long) = Action {
    implicit request =>
    val opinions = models.UserOpinion.findByHotelidAndCustid(hid, cid)
    Ok(Json.toJson(opinions))
  }

}