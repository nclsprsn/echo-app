package th.co.the1.echo.adapter.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import th.co.the1.echo.application.port.in.GetEchoUseCase;
import th.co.the1.echo.application.port.in.PostEchoUseCase;
import th.co.the1.echo.common.WebAdapter;

import java.util.Map;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class EchoController {

    private final GetEchoUseCase getEchoUseCase;
    private final PostEchoUseCase postEchoUseCase;

    @GetMapping(value = "/get", produces = "application/json")
    ResponseEntity<String> get(
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> parameters) {
        return ResponseEntity.ok(getEchoUseCase.get(GetEchoUseCase.GetEchoCommand.builder()
                .headers(headers)
                .parameters(parameters)
                .build()));
    }

    @PostMapping(value = "/post", produces = "application/json")
    ResponseEntity<String> post(@RequestHeader Map<String, String> headers,
                                @RequestParam Map<String, String> parameters,
                                @RequestBody String payload) {
        return ResponseEntity.ok(postEchoUseCase.post(PostEchoUseCase.PostEchoCommand.builder()
                .headers(headers)
                .parameters(parameters)
                .payload(payload)
                .build()));
    }
}
