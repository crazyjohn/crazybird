package com.crazybird.core.node.dispatch

import com.crazybird.core.msg.Message

trait NodeDispatcher {
  def dispatch(msg: Message)
}