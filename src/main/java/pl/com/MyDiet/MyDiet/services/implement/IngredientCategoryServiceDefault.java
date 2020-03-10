package pl.com.MyDiet.MyDiet.services.implement;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.DTO.IngredientDTO;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;
import pl.com.MyDiet.MyDiet.services.IngredientCategoryService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IngredientCategoryServiceDefault implements IngredientCategoryService {

    private final IngredientCategoryRepository ingredientCategoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public IngredientCategoryServiceDefault(IngredientCategoryRepository ingredientCategoryRepository, ModelMapper mapper) {
        this.ingredientCategoryRepository = ingredientCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<IngredientCategoryDTO> getAllIngredientCategoryDTO() {
        List<IngredientCategory> all = ingredientCategoryRepository.findAll();
        return all.stream().map(p -> mapper.map(p, IngredientCategoryDTO.class)).collect(Collectors.toSet());
    }

    @Override
    public Optional<IngredientCategory> findIngredientCategoryById(Long id) {
        return ingredientCategoryRepository.findById(id);
    }

    @Override
    public IngredientCategoryDTO findIngredientCategoryDTOByName(String name) {
        return mapper.map(ingredientCategoryRepository.findByName(name), IngredientCategoryDTO.class);
    }
    @Override
    public Set<IngredientCategoryDTO> getIngredientCategories(IngredientDTO ingredientDTO) {
        Set<IngredientCategoryDTO> allCategories =getAllIngredientCategoryDTO();
        Set<Long> usedCategories = ingredientDTO.getIngredientCategoriesIdAndName().stream()
                .map(IngredientDTO.IngredientCategoriesIdAndName::getId)
                .collect(Collectors.toSet());
        allCategories.removeIf(p -> usedCategories.contains(p.getId()));
        return allCategories;
    }
    @Override
    public boolean save(IngredientDTO ingredientDTO, String categoryName) {

        if (ingredientDTO == null
        || categoryName==null
        || ingredientCategoryRepository.existsByName(categoryName.toLowerCase().trim()))
            return false;

        String name = categoryName.toLowerCase().trim();
        IngredientCategory ingredientCategory = new IngredientCategory();
        ingredientCategory.setName(name);
        log.info("try to save ingredient category {}", name);
        ingredientCategoryRepository.save(ingredientCategory);
        ingredientCategory.setName(name);
        log.info("ingredient category {} saved", name);
        return true;
    }

    @Override
    public boolean delete(Long id, Long moderatorId) {
        //todo
        return false;
    }

    @Override
    public boolean modify(Long id, Long moderatorId) {
        return false;
    }

    @Override
    public boolean ingredientCategoryNameIsAvailable(String name) {
        return !ingredientCategoryRepository.existsByName(name);
    }
}
