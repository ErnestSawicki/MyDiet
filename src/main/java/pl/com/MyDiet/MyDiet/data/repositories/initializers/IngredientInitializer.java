package pl.com.MyDiet.MyDiet.data.repositories.initializers;

import lombok.extern.slf4j.Slf4j;
import pl.com.MyDiet.MyDiet.data.model.Ingredient;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientRepository;

@Slf4j
public class IngredientInitializer {

    public void createSampleIngredients(IngredientRepository ingredientRepository, IngredientCategoryRepository ingredientCategoryRepository) {

        IngredientCategory dairy = ingredientCategoryRepository.findByName("diary");
        createIngredient("cheese (ordinary)", 440L, dairy, ingredientRepository);
        createIngredient("cheese (Cheddar)", 260L, dairy, ingredientRepository);
        createIngredient("cottage cheese (low fat)", 80L, dairy, ingredientRepository);
        createIngredient("cottage cheese", 98L, dairy, ingredientRepository);
        createIngredient("cream cheese", 428L, dairy, ingredientRepository);
        createIngredient("custard", 100L, dairy, ingredientRepository);
        createIngredient("eggs", 150L, dairy, ingredientRepository);
        createIngredient("eggs fried", 180L, dairy, ingredientRepository);
        createIngredient("fromage frais", 125L, dairy, ingredientRepository);
        createIngredient("ice cream", 180L, dairy, ingredientRepository);
        createIngredient("milk whole", 70L, dairy, ingredientRepository);
        createIngredient("milk semi-skimmed", 50L, dairy, ingredientRepository);
        createIngredient("milk skimmed", 38L, dairy, ingredientRepository);
        createIngredient("milk soya", 36L, dairy, ingredientRepository);
        createIngredient("yogurt natural", 60L, dairy, ingredientRepository);
        createIngredient("yogurt natural (reduced fat)", 45L, dairy, ingredientRepository);

        IngredientCategory meat = ingredientCategoryRepository.findByName("meat");
        createIngredient("bacon average fried", 500L, meat, ingredientRepository);
        createIngredient("bacon average grilled", 380L, meat, ingredientRepository);
        createIngredient("beef (roast)", 280L, meat, ingredientRepository);
        createIngredient("beef burgers frozen", 280L, meat, ingredientRepository);
        createIngredient("chicken", 200L, meat, ingredientRepository);
        createIngredient("duck roast", 430L, meat, ingredientRepository);
        createIngredient("ham", 240L, meat, ingredientRepository);
        createIngredient("liver", 150L, meat, ingredientRepository);
        createIngredient("lamb", 300L, meat, ingredientRepository);
        createIngredient("pork", 290L, meat, ingredientRepository);
        createIngredient("rabbit", 180L, meat, ingredientRepository);
        createIngredient("turkey", 160L, meat, ingredientRepository);

        IngredientCategory fish = ingredientCategoryRepository.findByName("fish");
        createIngredient("anchovies tinned", 300L, fish, ingredientRepository);
        createIngredient("cockles", 50L, fish, ingredientRepository);
        createIngredient("cod fresh", 100L, fish, ingredientRepository);
        createIngredient("cod chip", 200L, fish, ingredientRepository);
        createIngredient("crab fresh", 110L, fish, ingredientRepository);
        createIngredient("fish fingers", 220L, fish, ingredientRepository);
        createIngredient("halibut fresh", 125L, fish, ingredientRepository);
        createIngredient("herring fresh grilled", 200L, fish, ingredientRepository);
        createIngredient("lobster boiled", 100L, fish, ingredientRepository);
        createIngredient("pilchards (tinned)", 140L, fish, ingredientRepository);
        createIngredient("prawns", 100L, fish, ingredientRepository);
        createIngredient("salmon fresh", 180L, fish, ingredientRepository);
        createIngredient("sardines tinned in oil", 220L, fish, ingredientRepository);
        createIngredient("sardines in tomato sauce", 180L, fish, ingredientRepository);
        createIngredient("trout fresh", 120L, fish, ingredientRepository);
        createIngredient("tuna tinned water", 100L, fish, ingredientRepository);
        createIngredient("tuna tinned oil", 180L, fish, ingredientRepository);

        IngredientCategory vegetables = ingredientCategoryRepository.findByName("vegetables");
        createIngredient("beans dried (boiled)", 130L, vegetables, ingredientRepository);
        createIngredient("broccoli", 32L, vegetables, ingredientRepository);
        createIngredient("cabbage (boiled)", 20L, vegetables, ingredientRepository);
        createIngredient("carrot (boiled)", 25L, vegetables, ingredientRepository);
        createIngredient("carrot", 30L, vegetables, ingredientRepository);
        createIngredient("cauliflower (boiled)", 30L, vegetables, ingredientRepository);
        createIngredient("celery (boiled)", 10L, vegetables, ingredientRepository);
        createIngredient("cucumber", 10L, vegetables, ingredientRepository);
        createIngredient("Lettuce", 15L, vegetables, ingredientRepository);
        createIngredient("mushrooms raw one average", 15L, vegetables, ingredientRepository);
        createIngredient("mushrooms (boiled)", 12L, vegetables, ingredientRepository);
        createIngredient("mushrooms (fried)", 145L, vegetables, ingredientRepository);
        createIngredient("olives", 80L, vegetables, ingredientRepository);
        createIngredient("onion (boiled)", 18L, vegetables, ingredientRepository);
        createIngredient("red onion", 80L, vegetables, ingredientRepository);
        createIngredient("onion (fried)", 155L, vegetables, ingredientRepository);
        createIngredient("peas", 148L, vegetables, ingredientRepository);
        createIngredient("peas dried & boiled", 120L, vegetables, ingredientRepository);
        createIngredient("pepper", 16L, vegetables, ingredientRepository);
        createIngredient("spinach", 8L, vegetables, ingredientRepository);
        createIngredient("sweetcorn", 130L, vegetables, ingredientRepository);
        createIngredient("sweetcorn on the cob", 70L, vegetables, ingredientRepository);
        createIngredient("tomato", 20L, vegetables, ingredientRepository);
        createIngredient("tomato cherry", 17L, vegetables, ingredientRepository);
        createIngredient("tomato puree", 70L, vegetables, ingredientRepository);
        createIngredient("potatoes (boiled)", 70L, vegetables, ingredientRepository);
        createIngredient("potatoes (roast)", 140L, vegetables, ingredientRepository);

        IngredientCategory fruits = ingredientCategoryRepository.findByName("fruits");
        createIngredient("apple", 50L, fruits, ingredientRepository);
        createIngredient("avocado", 160L, fruits, ingredientRepository);
        createIngredient("banana", 65L, fruits, ingredientRepository);
        createIngredient("blackberries", 25L, fruits, ingredientRepository);
        createIngredient("cherry", 50L, fruits, ingredientRepository);
        createIngredient("grapes", 62L, fruits, ingredientRepository);
        createIngredient("kiwi", 50L, fruits, ingredientRepository);
        createIngredient("melon", 28L, fruits, ingredientRepository);
        createIngredient("orange", 30L, fruits, ingredientRepository);
        createIngredient("peach", 38L, fruits, ingredientRepository);
        createIngredient("pear", 45L, fruits, ingredientRepository);
        createIngredient("pineapple", 40L, fruits, ingredientRepository);
        createIngredient("plum", 40L, fruits, ingredientRepository);

        IngredientCategory breadsAndCereals = ingredientCategoryRepository.findByName("breads&cereals");
        createIngredient("bagel", 310L, breadsAndCereals, ingredientRepository);
        createIngredient("bread white", 240L, breadsAndCereals, ingredientRepository);
        createIngredient("bread wholemeal", 220L, breadsAndCereals, ingredientRepository);
        createIngredient("biscuit digestives", 480L, breadsAndCereals, ingredientRepository);
        createIngredient("cornflakes", 370L, breadsAndCereals, ingredientRepository);
        createIngredient("crackerbread", 325L, breadsAndCereals, ingredientRepository);
        createIngredient("cream crackers", 440L, breadsAndCereals, ingredientRepository);
        createIngredient("crumpets", 198L, breadsAndCereals, ingredientRepository);
        createIngredient("macaroni (boiled)", 95L, breadsAndCereals, ingredientRepository);
        createIngredient("muesli", 390L, breadsAndCereals, ingredientRepository);
        createIngredient("naan bread", 320L, breadsAndCereals, ingredientRepository);
        createIngredient("noodles (boiled)", 70L, breadsAndCereals, ingredientRepository);
        createIngredient("pasta (normal boiled)", 110L, breadsAndCereals, ingredientRepository);
        createIngredient("pasta (wholemeal boiled)", 105L, breadsAndCereals, ingredientRepository);
        createIngredient("porridge oats", 55L, breadsAndCereals, ingredientRepository);
        createIngredient("rice (white boiled)", 140L, breadsAndCereals, ingredientRepository);
        createIngredient("rice (egg-fried)", 200L, breadsAndCereals, ingredientRepository);
        createIngredient("rice (brown)", 135L, breadsAndCereals, ingredientRepository);

        IngredientCategory sweets = ingredientCategoryRepository.findByName("sweets");
        createIngredient("chocolate", 500L, sweets, ingredientRepository);
        createIngredient("corn snack", 500L, sweets, ingredientRepository);
        createIngredient("crisps (chips US) average", 500L, sweets, ingredientRepository);
        createIngredient("honey", 280L, sweets, ingredientRepository);
        createIngredient("jam", 250L, sweets, ingredientRepository);
        createIngredient("mars bar", 480L, sweets, ingredientRepository);
        createIngredient("syrup", 300L, sweets, ingredientRepository);
        createIngredient("toffee", 400L, sweets, ingredientRepository);
        createIngredient("sugar", 400L, sweets, ingredientRepository);

        IngredientCategory fats = ingredientCategoryRepository.findByName("fats");
        createIngredient("butter", 750L, fats, ingredientRepository);
        createIngredient("lard", 890L, fats, ingredientRepository);
        createIngredient("margarine", 750L, fats, ingredientRepository);
        createIngredient("Oils - corn, sunflower,olive", 900L, fats, ingredientRepository);

    }


    private void createIngredient(String name, Long calories, IngredientCategory ingredientCategory, IngredientRepository repository) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredient.setCaloriesPer100gram(calories);
        ingredient.getIngredientCategories().add(ingredientCategory);
        repository.save(ingredient);
    }
}
