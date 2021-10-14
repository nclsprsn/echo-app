package th.co.the1.echo.application.port.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.Map;

public interface GetEchoUseCase {

    String get(GetEchoCommand command);

    @Value
    @Builder
    @Getter
    class GetEchoCommand {
        Map<String, String> headers;
        Map<String, String> parameters;
    }

}
