package th.co.the1.echo.application.port.out;

import th.co.the1.echo.domain.EchoRequest;

public interface EchoPort {
    String get(EchoRequest request);

    String post(EchoRequest request);
}
