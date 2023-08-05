package nekopiano.sandbox

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

object StreamDownloader extends {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  val connectionFlow = Http().outgoingConnectionTls(hostname)

  val responseFuture =
    Source.single(HttpRequest(uri = path, method = GET))
      .via(connectionFlow)
      .runWith(Sink.foreach[HttpResponse] { r =>
        logger.info(s"${r.status}")
      })
}
