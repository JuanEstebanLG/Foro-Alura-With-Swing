package com.alura.foro.foro.me.httpHandlers;

import com.alura.foro.foro.me.infra.exceptions.WebClientUnExpectedException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.nio.charset.StandardCharsets;

@Component
public class HttpRequestHandler {


    private static WebClient webClient;

    private HttpRequestHandler(WebClient webClient){
        HttpRequestHandler.webClient = webClient;
    }


    public static String sendLoginRequest(String nombre, String password) throws WebClientUnExpectedException {
        try {
            // URL de la solicitud
            String url = "http://localhost:8080";
            String JsonBodyRequest = String.format("{ \"nombre\" : \"%s\", \"password\" : \"%s\" }", nombre, password);
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
           throw new WebClientUnExpectedException(ex.getMessage());
        }

    }

    public static void getUserUI(String JWT){

    }
}
