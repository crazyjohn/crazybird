package com.crazybird.core.concurrent

import java.util.concurrent.atomic.AtomicLong
/**
 * Concurrent tools
 */
object Concurrent {
  // counter
  val counter = new AtomicLong

  /**
   *  thread factory
   *
   *  @param body by name parameter
   */
  def thread(body: => Unit, startNow: Boolean = true, threadName: String = s"Concurrent.Thread ${counter.incrementAndGet()}"): Thread = {
    // override the run method
    val thread = new Thread {
      override def run = body
    }
    // named
    thread setName threadName
    // start
    if (startNow) thread.start()
    // return
    thread
  }

  private def justPrint() = {
    println(s"[${Thread.currentThread().getName}]Create a new thread.")
    Thread.sleep(1000)
    println(s"[${Thread.currentThread().getName}]NewThread exit.")
  }

  private def test() {
    // new thread
    val newThread = thread {
      justPrint
    }
    newThread.join()
    // named
    val namedThread = thread(justPrint, true, "NamedThread")
  }

  def main(args: Array[String]) = {
    test
  }
}