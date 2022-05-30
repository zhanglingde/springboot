package com.ling.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        String url = "localhost:8088";

        ManagedChannel channel = ManagedChannelBuilder.forTarget(url)
                .usePlaintext()
                .build();

        HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder()
                .setId(1)
                .setName("zhangsan")
                .setAge(18)
                .build();
        HelloResponse response = stub.sayHello(request);
        System.out.println("response.getMessage() = " + response.getMessage());


    }
}
