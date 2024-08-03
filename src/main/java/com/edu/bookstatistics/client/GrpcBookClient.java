package com.edu.bookstatistics.client;

import com.deps.bookstatistics.BookServiceGrpc;
import com.deps.bookstatistics.BookRequest;
import com.deps.bookstatistics.BookResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcBookClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

        BookRequest request = BookRequest.newBuilder()
                .setTitle("Новое название")
                .setAuthor("Автор")
                .setTotalPages(44)
                .setCoverImage("http://example.com/cover.jpg")
                .build();

        BookResponse response = stub.addBook(request);

        System.out.println("Ответ от сервера: " + response);

        channel.shutdown();
    }
}