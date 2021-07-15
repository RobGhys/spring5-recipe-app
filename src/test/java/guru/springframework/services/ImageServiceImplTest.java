package guru.springframework.services;

import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {
    @Mock
    RecipeRepository recipeRepository;
    ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    public void saveImageFileTest() throws Exception {
        // Given
        Long id = 1L;
        MultipartFile multipartFile = new MockMultipartFile(
                "imagefile", "testing.txt",
                "text/plain",
                "Spring Framework Guru".getBytes());

        // Create a recipe object with id of "id"
        Recipe recipe = new Recipe();
        recipe.setId(id);
        Optional<Recipe> recipeOptional = Optional.of(recipe); // return of the findById method

        // When
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional); // Mock
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class); // For the recipe class
        imageService.saveImageFile(id, multipartFile); // Save returns "void", so we don't need to test a return value

        // Then
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue(); // Capture the argument
        assertEquals(multipartFile.getBytes().length, savedRecipe.getImage().length); // Make sure the bytes are the same
    }
}