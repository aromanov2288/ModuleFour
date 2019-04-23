package ru.romanov.modulefour.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanov.modulefour.page.BookPagesController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebFluxTest(BookPagesController.class)
@Import({ThymeleafAutoConfiguration.class})
public class BookPagesControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void showAllBooksTest() throws Exception {
        webClient.get().uri("/books/getAll").exchange().expectStatus().isOk();
    }

    @Test
    public void showAddBookPageTest() throws Exception {
        webClient.get().uri("/books/add").exchange().expectStatus().isOk();
    }

    @Test
    public void showEditBookPageTest() throws Exception {
        webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/books/edit").queryParam("id", "asd").build())
                .exchange()
                .expectStatus().isOk();

    }
}
