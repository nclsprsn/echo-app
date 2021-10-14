package th.co.the1.echo.domain;

import lombok.Data;

import java.util.Map;

@Data
public class EchoResponse {

    private String protocol;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private Map<String, String> parameters;
    private String path;
    private String payload;
}
