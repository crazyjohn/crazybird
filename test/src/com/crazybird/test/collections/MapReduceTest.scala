package com.crazybird.test.collections

object MapReduceTest extends App {
  val prices = Vector(100, 200, 300, 50, 400)
  val mapPrices = prices filter (_ >= 60) map (price => price + 10)
  println(mapPrices)
  println(s"prices ${mapPrices reduce ((price, total) => price + total)}")

  println(s"prices ${
    prices filter {
      _ >= 60
    } map {
      _ + 10
    } reduce {
      (price, total) => price + total
    }
  }")

  def filter60(price: Int) = price >= 60

  def add10(price: Int) = price + 10

  def addAll(price: Int, total: Int) = price + total

  println(s"prices ${prices filter filter60 map add10 reduce addAll}")
}