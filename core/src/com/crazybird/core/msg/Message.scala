package com.crazybird.core.msg

import org.apache.mina.core.buffer.IoBuffer

trait Message {
  def messageType: Short
  def read
  def write
  def setBuffer(buffer: IoBuffer)
}