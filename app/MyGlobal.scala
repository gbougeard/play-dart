import filters.LoggingFilter
import play.api._
import play.api.mvc.WithFilters
import play.filters.csrf.CSRFFilter
import play.filters.gzip.GzipFilter


/**
 * Created with IntelliJ IDEA.
 * User: gbougeard
 * Date: 02/04/13
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
object Global extends WithFilters(LoggingFilter, new GzipFilter(), new CSRFFilter()) {

  override def onStart(app: Application) {
    Logger.info("Start ")
  }

  override def onStop(app: Application) {
    Logger.info("Stop ")
  }

}
