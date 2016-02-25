package com.crazybird.test

trait Abstract {
  type T
  def transform(x: T): T
  val init: T
  var current: T
}

class Concrete extends Abstract {
  type T = String
  def transform(x: String) = x + x
  val init = "Init"
  var current = "Current"
}