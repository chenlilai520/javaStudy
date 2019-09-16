//package com.spring.springstudy.nio;
//
//import org.jboss.netty.bootstrap.ServerBootstrap;
//import org.jboss.netty.channel.*;
//import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.Executors;
//
///**
// * @author chenlilai
// * @title: HelloServe
// * @projectName javaStudy1
// * @description:
// * @date 2019/8/2020:54
// */
//public class HelloServe {
//
//
//    public static void main(String[] args) {
//
//
//        // Server服务启动器
//        ServerBootstrap bootstrap = new ServerBootstrap(
//                new NioServerSocketChannelFactory(
//                        Executors.newCachedThreadPool(),
//                        Executors.newCachedThreadPool()));
//
//
//        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
//                                         @Override
//                                         public ChannelPipeline getPipeline() throws Exception {
//                                             return Channels.pipeline(new HelloServerHandler());
//                                         }
//                                     }
//
//
//        );
//        bootstrap.bind(new InetSocketAddress(8000));
//    }
//
//    private static class HelloServerHandler extends
//            SimpleChannelHandler {
//
//        /**
//         * 当有client绑定到服务端的时候触发，打印"Hello world, I'm server."
//         */
//        @Override
//        public void channelConnected(
//                ChannelHandlerContext ctx,
//                ChannelStateEvent e) {
//            System.out.println("Hello world, I'm server.");
//        }
//
//    }
//}
