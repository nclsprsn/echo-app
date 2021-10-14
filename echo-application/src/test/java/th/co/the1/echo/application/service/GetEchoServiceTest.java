package th.co.the1.echo.application.service;

import org.mockito.Mockito;
import th.co.the1.echo.application.port.in.GetEchoUseCase;
import th.co.the1.echo.application.port.out.EchoCachePort;
import th.co.the1.echo.application.port.out.EchoPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

class GetEchoServiceTest {

    private final EchoPort echoPort = Mockito.mock(EchoPort.class);
    private final EchoCachePort echoCachePort = Mockito.mock(EchoCachePort.class);

    private final GetEchoService echoService = new GetEchoService(echoPort, echoCachePort);

    @org.junit.jupiter.api.Test
    void getEcho_shouldReturnsOkNoCache() {

        givenGetEchoWillSucceed();

        GetEchoUseCase.GetEchoCommand command = GetEchoUseCase.GetEchoCommand.builder()
                .headers(Map.of("key1", "value1", "key2", "value2"))
                .parameters(Map.of("key1", "value1", "key2", "value2"))
                .build();

        String result = echoService.get(command);

        assertThat(result).isEqualTo("{\n" +
                "  \"args\": {\n" +
                "    \"foo1\": \"bar1\",\n" +
                "    \"foo2\": \"bar2\"\n" +
                "  },\n" +
                "  \"headers\": {\n" +
                "    \"x-forwarded-proto\": \"https\",\n" +
                "    \"x-forwarded-port\": \"443\",\n" +
                "    \"host\": \"postman-echo.com\",\n" +
                "    \"x-amzn-trace-id\": \"Root=1-61656d07-777db106749d87c421755763\",\n" +
                "    \"user-agent\": \"PostmanRuntime/7.28.4\",\n" +
                "    \"accept\": \"*/*\",\n" +
                "    \"cache-control\": \"no-cache\",\n" +
                "    \"accept-encoding\": \"gzip, deflate, br\",\n" +
                "    \"cookie\": \"sails.sid=s%3ABAYN9ws71ckvciwxPhQQVG6-2fxVFzw5.8QSezk4EN7YAlr6dBRm8PRdDvHt17iT1tR8IQZwY5Fs\"\n" +
                "  },\n" +
                "  \"url\": \"https://postman-echo.com/get?foo1=bar1&foo2=bar2\"\n" +
                "}");

        then(echoPort).should(times(1)).get(any());
    }


    @org.junit.jupiter.api.Test
    void getEcho_shouldReturnsOkCache() {

        givenGetEchoWillSucceed();
        givenGetEchoCacheWillSucceed();

        GetEchoUseCase.GetEchoCommand command = GetEchoUseCase.GetEchoCommand.builder()
                .headers(Map.of("key1", "value1", "key2", "value2"))
                .parameters(Map.of("key1", "value1", "key2", "value2"))
                .build();

        String result = echoService.get(command);

        assertThat(result).isEqualTo("{\n" +
                "  \"args\": {\n" +
                "  },\n" +
                "  \"headers\": {\n" +
                "    \"x-forwarded-proto\": \"https\",\n" +
                "    \"x-forwarded-port\": \"443\",\n" +
                "    \"host\": \"postman-echo.com\",\n" +
                "    \"x-amzn-trace-id\": \"Root=1-61656d07-777db106749d87c421755763\",\n" +
                "    \"user-agent\": \"PostmanRuntime/7.28.4\",\n" +
                "    \"accept\": \"*/*\",\n" +
                "    \"cache-control\": \"no-cache\",\n" +
                "    \"accept-encoding\": \"gzip, deflate, br\",\n" +
                "    \"cookie\": \"sails.sid=s%3ABAYN9ws71ckvciwxPhQQVG6-2fxVFzw5.8QSezk4EN7YAlr6dBRm8PRdDvHt17iT1tR8IQZwY5Fs\"\n" +
                "  },\n" +
                "  \"url\": \"https://postman-echo.com/get?foo1=bar1&foo2=bar2\"\n" +
                "}");

        then(echoPort).should(times(0)).get(any());
        then(echoCachePort).should(times(1)).get(any());
    }

    private void givenGetEchoWillSucceed() {
        given(echoPort.get(any())).willReturn("{\n" +
                "  \"args\": {\n" +
                "    \"foo1\": \"bar1\",\n" +
                "    \"foo2\": \"bar2\"\n" +
                "  },\n" +
                "  \"headers\": {\n" +
                "    \"x-forwarded-proto\": \"https\",\n" +
                "    \"x-forwarded-port\": \"443\",\n" +
                "    \"host\": \"postman-echo.com\",\n" +
                "    \"x-amzn-trace-id\": \"Root=1-61656d07-777db106749d87c421755763\",\n" +
                "    \"user-agent\": \"PostmanRuntime/7.28.4\",\n" +
                "    \"accept\": \"*/*\",\n" +
                "    \"cache-control\": \"no-cache\",\n" +
                "    \"accept-encoding\": \"gzip, deflate, br\",\n" +
                "    \"cookie\": \"sails.sid=s%3ABAYN9ws71ckvciwxPhQQVG6-2fxVFzw5.8QSezk4EN7YAlr6dBRm8PRdDvHt17iT1tR8IQZwY5Fs\"\n" +
                "  },\n" +
                "  \"url\": \"https://postman-echo.com/get?foo1=bar1&foo2=bar2\"\n" +
                "}");
    }

    private void givenGetEchoCacheWillSucceed() {
        given(echoCachePort.get(any())).willReturn("{\n" +
                "  \"args\": {\n" +
                "  },\n" +
                "  \"headers\": {\n" +
                "    \"x-forwarded-proto\": \"https\",\n" +
                "    \"x-forwarded-port\": \"443\",\n" +
                "    \"host\": \"postman-echo.com\",\n" +
                "    \"x-amzn-trace-id\": \"Root=1-61656d07-777db106749d87c421755763\",\n" +
                "    \"user-agent\": \"PostmanRuntime/7.28.4\",\n" +
                "    \"accept\": \"*/*\",\n" +
                "    \"cache-control\": \"no-cache\",\n" +
                "    \"accept-encoding\": \"gzip, deflate, br\",\n" +
                "    \"cookie\": \"sails.sid=s%3ABAYN9ws71ckvciwxPhQQVG6-2fxVFzw5.8QSezk4EN7YAlr6dBRm8PRdDvHt17iT1tR8IQZwY5Fs\"\n" +
                "  },\n" +
                "  \"url\": \"https://postman-echo.com/get?foo1=bar1&foo2=bar2\"\n" +
                "}");
    }
}
