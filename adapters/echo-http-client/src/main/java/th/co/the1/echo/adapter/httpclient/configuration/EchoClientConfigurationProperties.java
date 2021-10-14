package th.co.the1.echo.adapter.httpclient.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class EchoClientConfigurationProperties {

    @Value("${echo-client.url}")
    private String url;

}
