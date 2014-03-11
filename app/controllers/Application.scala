package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Play.current.routes.foreach(r => println(r.documentation))
    Ok(views.html.index("Your new application is ready."))
  }

}