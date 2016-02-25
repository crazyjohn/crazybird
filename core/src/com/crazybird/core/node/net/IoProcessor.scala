package com.crazybird.core.node.net

import com.crazybird.core.node.LifeCycle
import org.apache.mina.transport.socket.nio.NioSocketAcceptor
import java.net.InetSocketAddress
import org.apache.mina.core.service.IoHandler
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.slf4j.LoggerFactory
import com.crazybird.core.logger.Logger
/**
 * The io processor
 */
class IoProcessor(ip: String, port: Int, ioHandler: IoHandler, codecFactory: ProtocolCodecFactory) extends LifeCycle {
  val acceptor = new NioSocketAcceptor
  // set handler and codecFactory
  acceptor setHandler ioHandler
  acceptor.getFilterChain.addLast("codec", new ProtocolCodecFilter(codecFactory))

  def startup = {
    acceptor.bind(new InetSocketAddress(ip, port))
    Logger.log(s"Node bind info: $ip, $port.")
  }

  def shutdown = {
    acceptor.unbind()
    acceptor.dispose()
  }

} 