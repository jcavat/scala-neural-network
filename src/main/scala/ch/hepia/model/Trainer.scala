package ch.hepia.model

import scala.util.Random

object Trainer {

  def train(perceptron: Perceptron, samples: List[Array[Double]], output: List[Double], generations: Int, rate: Double): Perceptron = {
    val r = new Random()
    for (_ <- 1 until generations) {

      val index = r.nextInt(samples.size)
      val sample: Array[Double] = samples(index)
      val observation = output(index)

      val prediction = perceptron.forward(sample)

      val err = observation - prediction

      perceptron.backtrack(err)
    }
    perceptron
  }
}
