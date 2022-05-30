package com.ling.helloworld;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Service {
    static Server server = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        server = ServerBuilder.forPort(8088)
                .addService(new HelloServiceImpl())
                .build()
                .start();
        System.out.println("服务器启动...");

        Thread.sleep(5000);
        new Thread(()->{
            try {
                Service.stop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        if (server != null) {
            server.awaitTermination();
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                try {
                    Service.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                System.err.println("*** server shut down");
            }
        });
    }

    private static void stop() throws InterruptedException {
        System.out.println("close...");
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    static class HelloServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            System.out.println("服务器收到【" + request.getId()+"-"+request.getName()+"】的消息");

            HelloResponse response = HelloResponse.newBuilder().setMessage("服务器已收到消息...").build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

}


