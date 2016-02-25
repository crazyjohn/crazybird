package com.crazybird.test
import scala.concurrent._
import concurrent.ExecutionContext.Implicits.global  

object FutureTest extends App {
  val s = "hello"
  
  val future: Future[String] = Future {
    s + " future"
  }
  
  future onSuccess {
    case msg => println(msg)
  }
  
  println(s)
}