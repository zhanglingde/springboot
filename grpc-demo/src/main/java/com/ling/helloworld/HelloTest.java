package com.ling.helloworld;


public class HelloTest {

    public static void main(String[] args) {
        HelloRequest.Builder builder = HelloRequest.newBuilder();
        HelloRequest user = builder.setId(1)
                .setName("zhangsan")
                .setAge(18)
                .build();
        System.out.println("user = " + user);
    }
}
