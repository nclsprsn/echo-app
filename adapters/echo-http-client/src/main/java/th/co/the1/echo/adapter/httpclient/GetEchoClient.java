package th.co.the1.echo.adapter.httpclient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;
import th.co.the1.echo.adapter.httpclient.configuration.EchoClientConfigurationProperties;
import th.co.the1.echo.application.port.out.EchoPort;
import th.co.the1.echo.domain.EchoRequest;

@Component
@RequiredArgsConstructor
public class GetEchoClient implements EchoPort {

    private final WebClient.Builder client;

    private final EchoClientConfigurationProperties properties;

    @Override
    public String get(EchoRequest request) {
        return client.baseUrl(properties.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build()
                .get()
                .uri(uriBuilder -> {
                            UriBuilder builder = uriBuilder.path("/get");
                            request.getParameters().forEach(builder::queryParam);
                            return builder.build();
                        }
                )
                .headers(httpHeaders -> request.getHeaders().forEach(httpHeaders::set))
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

    @Override
    public String post(EchoRequest request) {
        return client.baseUrl(properties.getUrl())
                .build()
                .post()
                .uri(uriBuilder -> {
                            UriBuilder builder = uriBuilder.path("/post");
                            request.getParameters().forEach(builder::queryParam);
                            return builder.build();
                        }
                )
                .headers(httpHeaders -> request.getHeaders().forEach(httpHeaders::set))
                .body(Mono.just(request.getPayload()), String.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

    }
}
