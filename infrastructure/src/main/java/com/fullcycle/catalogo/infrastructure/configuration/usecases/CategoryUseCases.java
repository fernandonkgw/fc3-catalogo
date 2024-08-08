package com.fullcycle.catalogo.infrastructure.configuration.usecases;

import com.fullcycle.catalogo.application.category.delete.DeleteCategoryUseCase;
import com.fullcycle.catalogo.application.category.list.ListCategoryUseCase;
import com.fullcycle.catalogo.application.category.save.SaveCategoryUseCase;
import com.fullcycle.catalogo.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class CategoryUseCases {

    private final CategoryGateway categoryGateway;

    public CategoryUseCases(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Bean
    DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    SaveCategoryUseCase saveCategoryUseCase() {
        return new SaveCategoryUseCase(categoryGateway);
    }

    @Bean
    ListCategoryUseCase listCategoryUseCase() {
        return new ListCategoryUseCase(categoryGateway);
    }
}
