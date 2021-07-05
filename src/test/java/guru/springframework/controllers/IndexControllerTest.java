package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class IndexControllerTest {
    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        // 1) Given
        Set<Recipe> recipes = new HashSet<>();

        // Add 2 recipes to recipes
        recipes.add(new Recipe());
        Recipe anotherRecipe = new Recipe();
        anotherRecipe.setId(1L);
        recipes.add(anotherRecipe);

        when(recipeService.getRecipes()).thenReturn(recipes);
        // Create an ArgumentCaptor for the class Set
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // 2) When
        String viewName = indexController.getIndexPage(model);

        // 3) Then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();

        // To test when we add the map-key String of "recipes" to the model
        // The value returned is going to be a Set. So we ask for argumentCaptor.capture()
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();

        // Verify that we get back 2 items from recipes (that we added above)
        assertEquals(2, setInController.size());

    }
}