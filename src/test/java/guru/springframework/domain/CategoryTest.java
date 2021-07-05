package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    Category category;

    @Before
    public void setUp() {
        category = new Category(); // Get a new Category when we run the program, before each Test is executed.
    }

    @Test
    public void getId() {
        Long idValue = 4L;

        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}