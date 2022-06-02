package http;

import java.util.Objects;

public record JsonHttpResponse(int code, String json) implements HttpResponse {

}

