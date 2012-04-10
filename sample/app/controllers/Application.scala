package controllers

import play.api._
import play.api.mvc._
import models._
import se.radley.plugin.mongodb.salat._
import com.mongodb.casbah.Imports._
import com.novus.salat._

object Application extends Controller {
  
  def list() = Action {
    val users = User.all.toList
    Ok(views.html.list(users))
  }

  def listByCountry(country: String) = Action {
    val users = User.findByCountry(country).toList
    Ok(views.html.list(users))
  }

  def create(username: String) = Action {
    val user = User(
      username = username,
      password = "1234"
    )
    User.save(user)
    Ok(views.html.user(user))
  }
  
}