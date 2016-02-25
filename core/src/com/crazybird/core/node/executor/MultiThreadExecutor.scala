package com.crazybird.core.node.executor

import scala.collection.mutable.ListBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.crazybird.core.node.LifeCycle
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ThreadFactory
import com.crazybird.core.logger.Logger
/**
 * MultiThreadExecutor
 */
class MultiThreadExecutor(name: String, threadCount: Int) extends NodeExecutor with LifeCycle {
  require(threadCount > 0)
  val executors = new ListBuffer[ExecutorService]
  for (i <- 0 until threadCount) executors += Executors.newSingleThreadExecutor(new ThreadFactory() {
    override def newThread(run: Runnable) = {
      new Thread(run, s"$name-$i")
    }
  })

  private def active = {
    executors.foreach {
      _.execute(new Runnable() {
        def run() = {
          Logger.log(s"Executors already actived: [${Thread.currentThread().getName}].")
        }
      })
    }

  }

  def execute(obj: Any)(body: => Unit) {
    executors(generateKey(obj) % executors.length).execute(new Runnable() {
      override def run = body
    })
  }

  class Poision(latch: CountDownLatch) extends Runnable {
    override def run = latch.countDown()
  }

  def startup = active

  def shutdown = {
    // latch
    val latch = new CountDownLatch(this.executors.size)
    // eat poision
    executors.foreach { _.submit(new Poision(latch)) }
    // await
    latch.await()
    // shutdownNow
    executors.foreach { _.shutdownNow() }
  }

  // test case
  private def testExecute() {
    execute(new AnyRef) {
      println("testExecute")
    }
  }
}