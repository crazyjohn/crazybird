package com.crazybird.game

import com.crazybird.core.node.NodeConfig
import com.crazybird.core.node.ServerNode
import com.crazybird.core.logger.Logger
import com.crazybird.core.codec.ProtobufCodecFactory

object GameServerApp extends App {
  // engine
  val engineName = "CrazyBird"
  Logger.log(s"This game engine, i use $engineName")
  // node 
  val config = new NodeConfig
  val node = new ServerNode(config, new GameIoHandler, new ProtobufCodecFactory)
  node.startup
}