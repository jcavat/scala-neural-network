package ch.hepia

import ch.hepia.model.{Perceptron, SimplePerceptron, Trainer}

object Main extends App {
  def and(): Perceptron = {
    val sample: List[Array[Double]] = List(Array(0,0), Array(0,1), Array(1,0), Array(1,1))
    val output: List[Double] = List(0,0,0,1)
    val sp = SimplePerceptron(2, 0.1)
    Trainer.train(sp, sample, output, 1000, 0.1)
    sp
  }
  def or(): Perceptron = {
    val sample: List[Array[Double]] = List(Array(0,0), Array(0,1), Array(1,0), Array(1,1))
    val output: List[Double] = List(0,1,1,1)
    val sp = SimplePerceptron(2, 0.1)
    Trainer.train(sp, sample, output, 1000, 0.1)
    sp
  }

  val n = or()
  n.printWeight()

  for (s <- List(Array(0,0), Array(0,1), Array(1,0), Array(1,1)): List[Array[Double]]) {
    println(n.predict(s))
  }

}

