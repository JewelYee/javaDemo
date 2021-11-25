package com.yee.demo.netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @Desciption:
 * @Auther: yee
 * @Date:2021/11/25 2:47 PM
 */
@Sharable
public class TCPServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 日志
     */
    private static Log LOGGER = LogFactory.getLog(TCPServerHandler.class);

    /**
     * 每一个channel，都有独立的handler、ChannelHandlerContext、ChannelPipeline、Attribute
     * 所以不需要担心多个channel中的这些对象相互影响。<br>
     * 这里我们使用content这个key，记录这个handler中已经接收到的客户端信息。
     */
    private static AttributeKey<StringBuffer> content = AttributeKey.valueOf("content");

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRegistered(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelRegistered(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelUnregistered(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelUnregistered(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelActive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelActive(ctx) = " + ctx.toString());
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelInactive(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelRead(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        TCPServerHandler.LOGGER.info("channelRead(ChannelHandlerContext ctx, Object msg)");
        /*
         * 我们使用IDE工具模拟长连接中的数据缓慢提交。
         * 由read方法负责接收数据，但只是进行数据累加，不进行任何处理
         * */
        ByteBuf byteBuf = (ByteBuf)msg;
        try {
            StringBuffer contextBuffer = new StringBuffer();
            while(byteBuf.isReadable()) {
                contextBuffer.append((char)byteBuf.readByte());
            }

            //加入临时区域
            StringBuffer content = ctx.attr(TCPServerHandler.content).get();
            if(content == null) {
                content = new StringBuffer();
                ctx.attr(TCPServerHandler.content).set(content);
            }
            content.append(contextBuffer);
        } catch(Exception e) {
            throw e;
        } finally {
            byteBuf.release();
        }
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelReadComplete(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelReadComplete(ChannelHandlerContext ctx)");
        /*
         * 由readComplete方法负责检查数据是否接收完了。
         * 和之前的文章一样，我们检查整个内容中是否有“over”关键字
         * */
        StringBuffer content = ctx.attr(TCPServerHandler.content).get();
        //如果条件成立说明还没有接收到完整客户端信息
        if(content.indexOf("over") == -1) {
            return;
        }

        //当接收到信息后，首先要做的的是清空原来的历史信息
        ctx.attr(TCPServerHandler.content).set(new StringBuffer());

        //准备向客户端发送响应
        ByteBuf byteBuf = ctx.alloc().buffer(1024);
        byteBuf.writeBytes("回发响应信息！".getBytes());
        ctx.writeAndFlush(byteBuf);

        /*
         * 关闭，正常终止这个通道上下文，就可以关闭通道了
         * （如果不关闭，这个通道的回话将一直存在，只要网络是稳定的，服务器就可以随时通过这个回话向客户端发送信息）。
         * 关闭通道意味着TCP将正常断开，其中所有的
         * handler、ChannelHandlerContext、ChannelPipeline、Attribute等信息都将注销
         * */
        ctx.close();
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#userEventTriggered(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        TCPServerHandler.LOGGER.info("super.userEventTriggered(ctx, evt)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelWritabilityChanged(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.channelWritabilityChanged(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#exceptionCaught(io.netty.channel.ChannelHandlerContext, java.lang.Throwable)
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        TCPServerHandler.LOGGER.info("super.exceptionCaught(ctx, cause)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelHandlerAdapter#handlerAdded(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.handlerAdded(ctx)");
    }

    /* (non-Javadoc)
     * @see io.netty.channel.ChannelHandlerAdapter#handlerRemoved(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        TCPServerHandler.LOGGER.info("super.handlerRemoved(ctx)");
    }
}
