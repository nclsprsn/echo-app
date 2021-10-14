package th.co.the1.echo.application.port.out;

public interface EchoCachePort {

    String get(String key);

    void put(String key, String value);

}
