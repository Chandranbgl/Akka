import Main.system
import akka.actor.{Actor, ActorSystem, Props}

class HelloActor2 extends Actor {
  override def receive: Receive = {
    case "world" => println("i'm in HelloActor2")
    case _ => println("None")
  }
}

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       =>
      val helloActor2 = system.actorOf(Props[HelloActor2], name = "helloactor2")
      helloActor2 ! "world"
      Thread.sleep(5000)
      println("huh?")
  }
}

object Main extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}