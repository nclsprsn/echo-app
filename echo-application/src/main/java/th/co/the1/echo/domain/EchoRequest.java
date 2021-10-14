package th.co.the1.echo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class EchoRequest {
    private String protocol;
    private String method;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private String path;
    private String payload;
}
