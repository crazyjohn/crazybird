package com.crazybird.core.node

import com.crazybird.core.node.dispatch.NodeDispatcher
import com.crazybird.core.msg.Message
import com.crazybird.core.node.executor.MultiThreadExecutor
import org.slf4j.LoggerFactory
import com.crazybird.core.logger.Logger
import com.crazybird.core.node.net.IoProcessor
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.core.service.IoHandler

class ServerNode(config: NodeConfig, ioHandler: IoHandler, codecFactory: ProtocolCodecFactory) extends Node {
  // executor
  protected val executor = new MultiThreadExecutor("ServerNodeExecutor", config.msgThreadCount)
  // dispatcher
  protected val dispatcher = new NodeDispatcher {
    override def dispatch(msg: Message) = {}
  }
  // ioProcessor
  protected val ioProcessor = new IoProcessor(config.ip, config.port, ioHandler, codecFactory)

  override def configure(config: NodeConfig) = {}

  /**
   * FIXME: crazyjohn why must use test execute for executor, otherwise the thread can't start???
   */
  def startup = {
    executor.startup
    Logger.log("Node startup.")
    // io
    ioProcessor.startup
  }

  def shutdown = executor.shutdown
}