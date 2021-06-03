package api.Handlers;


import api.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import java.util.List;
import io.vavr.control.Try;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


public abstract class Handler {

    private final ObjectMapper objectMapper;

    public Handler(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public void handle(HttpExchange exchange){
        Try.run(()-> execute(exchange)).onFailure(thr -> System.out.println(thr + " " +  exchange));
    }

    protected abstract void execute(HttpExchange exchange) throws Exception;

    protected <T> byte[] writeResp (T response){
        try {
            return objectMapper.writeValueAsBytes(response);
        } catch (IOException e) {
            return "Invalid request".getBytes();
        }
    }
    protected <T> T readResp (InputStream is, Class<T> type){
        try {
            return objectMapper.readValue(is, type);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    protected Headers getHeaders (String key, String value){
        Headers headers = new Headers();
        headers.add(key, value);
        headers.add("Access-Control-Allow-Origin", "*");


        return headers;
    }

}
