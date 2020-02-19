package pl.com.MyDiet.MyDiet.services;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.MyDiet.MyDiet.DTO.IngredientCategoryDTO;
import pl.com.MyDiet.MyDiet.data.model.IngredientCategory;
import pl.com.MyDiet.MyDiet.data.repositories.IngredientCategoryRepository;

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
    public Optional<IngredientCategory> findById(Long id) {
        return ingredientCategoryRepository.findById(id);
    }
}
