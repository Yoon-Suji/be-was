package dto;

import java.util.Map;

public class HttpRequestDtoBuilder {
    private final String method;

    private final String uri;

    private final String httpVersion;

    private Map<String, String> headers;

    private String body;

    public HttpRequestDtoBuilder(String method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
    }

    public HttpRequestDtoBuilder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public HttpRequestDtoBuilder setBody(String body) {
        this.body = body;
        return this;
    }

    public HttpRequestDto build() {
        return new HttpRequestDto(method, uri, httpVersion, headers, body);
    }
}
