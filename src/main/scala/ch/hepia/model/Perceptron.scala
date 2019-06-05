package ch.hepia.model

trait Perceptron {

  val size: Int
  val rdm = new scala.util.Random

  var ws: Array[Double] = (0 to size).map(_ => rdm.nextDouble() ).toArray
  var input: Array[Double] = new Array(0)

  def rate: Double

  def sum(data: Array[Double]): Double = {
    ws.zip(1.0 +: data).map( tpl => tpl._1 * tpl._2 ).sum
  }

  def predict(data: Array[Double]): Double

  def forward(data: Array[Double]): Double = {
    input = data.clone()
    predict(data)
  }

  def backtrack(deltaError: Double): Unit = {
    val corr: Array[Double] = ws.zip(1.0 +: input).map{ case (w: Double, d: Double) => rate * deltaError * d }
    ws = ws.zip(corr).map{ case (w: Double, e: Double) => w + e }
  }

  def printWeight(): Unit = {
    ws.foreach( w => print(w +";") )
    println()
  }
}

case class SimplePerceptron(size: Int, rate: Double) extends Perceptron {
  def predict(data: Array[Double]): Double = if (sum(data) >= 0.0) 1.0 else 0.0
}

case class DoublePerceptron(size: Int, rate: Double) extends Perceptron {
  def predict(data: Array[Double]): Double = sum(data)
}

case class SigmoidPerceptron(size: Int, rate: Double) extends Perceptron {
  def predict(data: Array[Double]): Double = 1.0 / ( 1.0 + scala.math.exp( -sum(data) ) )
}

