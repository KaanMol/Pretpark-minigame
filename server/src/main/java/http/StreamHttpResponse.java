package http;

public record StreamHttpResponse(String mime, byte[] bytes) implements HttpResponse {

}
