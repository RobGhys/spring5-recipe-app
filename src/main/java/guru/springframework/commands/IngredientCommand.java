package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private Long recipeId; // Only property we will need from the recipe
    private String description;
    private BigDecimal amount;
    private UnitOfMeasureCommand uom;
}
