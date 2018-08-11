package daos

import java.sql.Timestamp

import javax.inject.{Inject, Singleton}
import models.TinyUrl
import org.joda.time.DateTime
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape
import utils.FutureOpt

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

@Singleton
class SlickTinyUrlDao @Inject()(val dbConfigProvider: DatabaseConfigProvider)
  extends TinyUrlDao with HasDatabaseConfigProvider[JdbcProfile]
{
  import profile.api._

  class TinyUrls(tag: Tag) extends Table[TinyUrl](tag, "tiny_urls")
  {
    implicit val dateTimeColumnType: BaseColumnType[DateTime] =
      MappedColumnType.base[DateTime, Timestamp](
        dateTime => new Timestamp(dateTime.getMillis),
        timeStamp => new DateTime(timeStamp.getTime)
      )

    def key: Rep[String] = column[String]("url_key", O.PrimaryKey)
    def createdAt: Rep[DateTime] = column[DateTime]("created_at")
    def longUrl: Rep[String] = column[String]("long_url")

    override def * : ProvenShape[TinyUrl] = (key, createdAt, longUrl) <> (TinyUrl.apply _ tupled, TinyUrl.unapply)
  }

  val tinyUrlTable = TableQuery[TinyUrls]

  override def insert(tinyUrl: TinyUrl)(implicit executionContext: ExecutionContext): Future[TinyUrl] =
    db.run(tinyUrlTable += tinyUrl).map(_ => tinyUrl)

  override def getByKey(key: String)(implicit executionContext: ExecutionContext): FutureOpt[TinyUrl] =
    FutureOpt {
      db.run(tinyUrlTable.filter(_.key === key).result.headOption)
    }
}
