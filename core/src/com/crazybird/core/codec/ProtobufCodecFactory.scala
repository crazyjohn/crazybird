package com.crazybird.core.codec

import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFactory
import org.apache.mina.filter.codec.ProtocolDecoder
import org.apache.mina.filter.codec.ProtocolEncoder
import org.apache.mina.filter.codec.ProtocolEncoderOutput
import org.apache.mina.core.buffer.IoBuffer
import org.apache.mina.filter.codec.ProtocolDecoderOutput
import com.crazybird.core.msg.ProtobufMessage
import com.crazybird.core.logger.Logger._

class ProtobufCodecFactory extends ProtocolCodecFactory {
  def getEncoder(session: IoSession): ProtocolEncoder = new ProtocolEncoder() {
    def encode(session: IoSession, msg: Object, output: ProtocolEncoderOutput) = {
      val protoMsg = msg match {
        case ProtobufMessage => msg.asInstanceOf[ProtobufMessage]
        case _ => log(s"Unknown type: ${msg.getClass.getSimpleName}")
      }
      val buffer = IoBuffer.allocate(64)
    }
    def dispose(session: IoSession) = {}
  }

  def getDecoder(session: IoSession): ProtocolDecoder = new ProtocolDecoder() {
    def decode(session: IoSession, buffer: IoBuffer, output: ProtocolDecoderOutput) = {}
    def finishDecode(sesssion: IoSession, output: ProtocolDecoderOutput) = {}
    def dispose(session: IoSession) = {}
  }
}