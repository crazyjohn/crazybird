package com.crazybird.core.msg

import org.apache.mina.core.buffer.IoBuffer

case class ProtobufMessage(val messageType: Short) extends Message {
  var buffer: IoBuffer = null
  def read = {}
  def write = {}
  def setBuffer(buffer: IoBuffer) = { this.buffer = buffer }
}