package gateway;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

  @Autowired private ApplicationContext context;

  private WebTestClient webClient;

  @Before
  public void setup() {
    this.webClient =
        WebTestClient.bindToApplicationContext(this.context).apply(springSecurity()).build();
  }

  @Test
  public void testUnauthenticated() {
    webClient
        .get()
        .uri("/example")
        .exchange()
        .expectStatus()
        .is3xxRedirection();
  }

  @Test
  public void testAuthenticated() throws Exception {
    webClient
        .mutateWith(mockJwt())
        .get()
        .uri("/example")
        .exchange()
        .expectStatus()
        .is2xxSuccessful();
  }
}
