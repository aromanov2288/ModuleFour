package ru.romanov.modulefour.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.romanov.modulefour.page.BookPagesController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookPagesController.class)
public class BookPagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void showAllBooksTest() throws Exception {
        mockMvc.perform(get("/books/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void showAddBookPageTest() throws Exception {
        mockMvc.perform(get("/books/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void showEditBookPageTest() throws Exception {
        mockMvc.perform(get("/books/edit").param("id", "1"))
                .andExpect(status().isOk());
    }
}
