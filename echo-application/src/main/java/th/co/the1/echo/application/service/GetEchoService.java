package th.co.the1.echo.application.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import th.co.the1.echo.application.port.in.GetEchoUseCase;
import th.co.the1.echo.application.port.out.EchoCachePort;
import th.co.the1.echo.application.port.out.EchoPort;
import th.co.the1.echo.common.UseCase;
import th.co.the1.echo.domain.EchoRequest;

@UseCase
@RequiredArgsConstructor
public class GetEchoService implements GetEchoUseCase {

    private static final Logger log = LoggerFactory.getLogger(GetEchoService.class);

    private final static String KEY = "get";

    private final EchoPort getEchoPort;

    private final EchoCachePort echoCachePort;

    @Override
    public String get(GetEchoCommand command) {
        String cachedContent = echoCachePort.get(KEY);
        if (cachedContent != null) {
            return cachedContent;
        }
        String response = getEchoPort.get(EchoRequest.builder()
                .headers(command.getHeaders())
                .parameters(command.getParameters())
                .build());
        echoCachePort.put(KEY, response);
        return response;
    }
}
