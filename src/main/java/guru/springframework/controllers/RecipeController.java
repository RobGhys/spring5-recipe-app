package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")// Only Get http method will be supported by this function
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return "recipe/show";
    }

    @GetMapping("recipe/new")// Only Get http method will be supported by this function
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform"; // view name that matches recipeform.html
    }

    @GetMapping("recipe/{id}/update")// Only Get http method will be supported by this function
    public String updateRecipe(@PathVariable String id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    // @ModelAttribute is used to bind the form post parameters to the recipe command object
    // It will happen automatically thanks to the naming conventions that we used in the form
    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        // Redirect to a specific URL
        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    // Only Get http method will be supported by this function
    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id) {
        //log.debug("Deleteing id# " + id);
        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
