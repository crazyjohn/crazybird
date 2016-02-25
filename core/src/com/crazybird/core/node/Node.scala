package com.crazybird.core.node

trait Node extends LifeCycle {
  def configure(config: NodeConfig)
}