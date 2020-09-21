package $package$

import cats.effect._
import cats.implicits._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {
    IO(println("I am a new project!")).as(ExitCode.Success)
  }

}
