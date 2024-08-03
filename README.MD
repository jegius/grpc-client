# grpc-client

`grpc-client` — это проект-клиент, который взаимодействует с gRPC сервисом для выполнения операций над книгами. Проект предоставляет клиента, который отправляет запросы к gRPC серверу и получает ответы.

## Содержание

- [Описание](#описание)
- [Установка](#установка)
- [Использование](#использование)
- [Публикация через JitPack](#публикация-через-jitpack)
  - [Шаг 1: Подготовьте проект](#шаг-1-подготовьте-проект)
  - [Шаг 2: Создайте файл GitHub репозитория](#шаг-2-создайте-файл-github-репозитория)
  - [Шаг 3: Сборка и публикация проекта](#шаг-3-сборка-и-публикация-проекта)
- [Примеры кода](#примеры-кода)
  - [GrpcBookClient](#grpcbookclient)

## Описание

Этот клиент использует сгенерированные интерфейсы gRPC для взаимодействия с сервером, предоставляющим методы для работы с книгами.

## Установка

Для установки и настройки проекта выполните следующие шаги:

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/Ваша_организация/Ваш_репозиторий.git
   cd Ваш_репозиторий
   ```

2. Убедитесь, что у вас установлена Java 17 и Maven.

3. Соберите проект:
   ```bash
   mvn clean install
   ```

## Использование

Для отправки gRPC запросов к серверу выполните следующий код:

```java
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
```

## Примеры кода

### GrpcBookClient

Пример кода клиента для gRPC:

```java
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
```

Это простой пример кода, который создаёт gRPC клиент, подключается к серверу, отправляет запрос на добавление книги и получает ответ.

## Конфигурация Maven

Основные зависимости и конфигурации проекта описаны в `pom.xml` файле:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.edu.bookstatistics</groupId>
    <artifactId>grpc-client</artifactId>
    <version>0.0.1-SNAPSHOT</version>

    <properties>
        <java.version>17</java.version>
        <grpc.version>1.42.1</grpc.version>
    </properties>

    <dependencies>
        <!-- gRPC -->
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-netty-shaded</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-protobuf</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-stub</artifactId>
            <version>${grpc.version}</version>
        </dependency>
        <dependency>
            <groupId>com.github.jegius</groupId>
            <artifactId>grpc-interfaces</artifactId>
            <version>main-dd5c88019-1</version>
        </dependency>
    </dependencies>
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
</project>
```

Посмотрите документ по использованию JitPack для актуальной информации: [официальная документация JitPack](https://jitpack.io/docs/).

