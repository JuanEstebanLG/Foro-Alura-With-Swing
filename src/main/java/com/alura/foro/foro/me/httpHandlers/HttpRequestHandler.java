package com.alura.foro.foro.me.httpHandlers;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.nio.charset.StandardCharsets;

@Component
public class HttpRequestHandler {


    private static WebClient webClient;

    public HttpRequestHandler(WebClient webClient){
        this.webClient = webClient;
    }


    public static String sendHttpRequest(String name, String password) {
        try {
            // URL de la solicitud
            String url = "http://localhost:8080";
            String JsonBodyRequest = String.format("{ \"nombre\" : \"%s\", \"password\" : \"%s\" }", name, password);
            WebClient client = WebClient.create();

            WebClient.RequestBodySpec request = webClient.post().uri(url + "/logins");

            WebClient.RequestHeadersSpec<?> headersSpec =
                    request.contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(JsonBodyRequest)
                    ;

            WebClient.ResponseSpec responseSpec = headersSpec
                    .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                    .acceptCharset(StandardCharsets.UTF_8)
                    .retrieve();


            return responseSpec.bodyToMono(String.class).block();

        } catch (Exception ex) {
            ex.printStackTrace();
           throw new RuntimeException(ex);
        }

    }
}
