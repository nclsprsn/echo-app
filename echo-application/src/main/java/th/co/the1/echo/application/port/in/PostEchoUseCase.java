package th.co.the1.echo.application.port.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Map;

public interface PostEchoUseCase {

    String post(PostEchoCommand command);

    @Value
    @Builder
    @Getter
    class PostEchoCommand {
        Map<String, String> headers;
        Map<String, String> parameters;
        String payload;
    }

}
