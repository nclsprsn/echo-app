package th.co.the1.echo.adapter.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import th.co.the1.echo.application.port.in.GetEchoUseCase;
import th.co.the1.echo.application.port.in.PostEchoUseCase;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EchoController.class)
class EchoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetEchoUseCase getEchoUseCase;

    @MockBean
    private PostEchoUseCase postEchoUseCase;

    @Test
    void getisOk() throws Exception {
        mockMvc.perform(get("/get")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        then(getEchoUseCase).should()
                .get(any());
    }

    @Test
    void postisOk() throws Exception {
        mockMvc.perform(post("/post")
                        .content("Some data")
                        .header("Content-Type", "application/json"))
                .andExpect(status().isOk());

        then(postEchoUseCase).should()
                .post(any());
    }

}
