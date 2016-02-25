package com.crazybird.core.node.executor

trait NodeExecutor {
  def execute(obj: Any)(body: => Unit)

  protected def generateKey(obj: Any) = {
    val key = obj.hashCode()
    key
  }
}