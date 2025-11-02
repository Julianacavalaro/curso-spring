package org.example.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class JsonReaderDemo {

    private static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) {
      //  lercomScanner();
        lerComHttpClient();
    }

    private static void lerComHttpClient() {
        try (HttpClient client = HttpClient.newBuilder().build()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dummyjson.com/posts/1"))
                    .build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(stringHttpResponse -> convertJsonToPost(stringHttpResponse.body()))
               //     .thenAccept(System.out::println)
                    .thenAccept(post -> System.out.println(post.getTitle()))
                    .join();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    private static void lercomScanner() {
        try (Scanner scanner = new Scanner(new URL("https://dummyjson.com/posts/1").openStream())) {
            String json = scanner.nextLine();
            System.out.println(json);
            Post post = convertJsonToPost(json);
            System.out.println(post);
            System.out.println(post.getTitle());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @SneakyThrows //relança a exceção em tempo de execução
    private static Post convertJsonToPost(String json){
        return mapper.readValue(json,Post.class);
    }
}
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Post{
    private Long id;
    private String title;
    private String body;
    private Long userId;
    private Long views;
    private String[] tags;
    private Reaction reactions;

}
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Reaction {
    private Long likes;
    private Long dislikes;
}