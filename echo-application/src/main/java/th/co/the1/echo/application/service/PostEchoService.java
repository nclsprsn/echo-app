package th.co.the1.echo.application.service;

import lombok.RequiredArgsConstructor;
import th.co.the1.echo.application.port.in.PostEchoUseCase;
import th.co.the1.echo.application.port.out.EchoPort;
import th.co.the1.echo.common.UseCase;
import th.co.the1.echo.domain.EchoRequest;

@UseCase
@RequiredArgsConstructor
public class PostEchoService implements PostEchoUseCase {

    private final EchoPort getEchoPort;

    @Override
    public String post(PostEchoCommand command) {
        return getEchoPort.post(EchoRequest.builder()
                .headers(command.getHeaders())
                .parameters(command.getParameters())
                .payload(command.getPayload())
                .build());
    }
}
