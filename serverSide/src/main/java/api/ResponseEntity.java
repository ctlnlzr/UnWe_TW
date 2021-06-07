package api;

public class ResponseEntity<T> {
    private final T body;
    private final int statusCode;

    public ResponseEntity(T body,  int statusCode) {
        this.body = body;
       this.statusCode = statusCode;
    }

    public T getBody() {
        return body;
    }



    public int getStatusCode() {
        return statusCode;
    }
}
