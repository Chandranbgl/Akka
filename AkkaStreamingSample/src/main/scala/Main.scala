
import akka.{Done, NotUsed}
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

object Main extends App {
  println("Hello Akka")

  implicit val actorSystem = ActorSystem()
  import actorSystem.dispatcher
  implicit val flowMaterializer = ActorMaterializer()
  val input = Source(1 to 100)
  val normalize = Flow[Int].map(_ * 2)
  val output = Sink.foreach[Int](println)
  input.via(normalize).runWith(output).andThen {
    case _ =>
      actorSystem.terminate()
  }
}