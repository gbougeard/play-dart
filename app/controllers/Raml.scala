package controllers

import play.api._
import play.api.mvc._

object Raml extends Controller {

  def console = Action {
    Ok(views.html.raml.console("Api documentation"))
  }

  def editor = Action {
    Ok(views.html.raml.editor("Api editor"))
  }

}