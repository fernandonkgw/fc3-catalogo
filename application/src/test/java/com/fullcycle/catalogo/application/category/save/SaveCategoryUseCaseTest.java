package com.fullcycle.catalogo.application.category.save;

import com.fullcycle.catalogo.application.UseCaseTest;
import com.fullcycle.catalogo.domain.Fixture;
import com.fullcycle.catalogo.domain.category.Category;
import com.fullcycle.catalogo.domain.category.CategoryGateway;
import com.fullcycle.catalogo.domain.exceptions.DomainException;
import com.fullcycle.catalogo.domain.utils.InstantUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SaveCategoryUseCaseTest extends UseCaseTest {

    @InjectMocks
    private SaveCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @Test
    void givenValidCategory_whenCallsSave_shouldPersistIt() {
        // given
        final var aCategory = Fixture.Categories.aulas();
        when(categoryGateway.save(any()))
                .thenAnswer(returnsFirstArg());
        // when
        this.useCase.execute(aCategory);
        // then
        verify(categoryGateway, times(1)).save(aCategory);
    }

    @Test
    void givenInvalidNullCategory_whenCallsSave_shouldReturnError() {
        // given
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'aCategory' cannot be null";
        final Category aCategory = null;
        // when
        final var actualError = assertThrows(DomainException.class, () -> this.useCase.execute(aCategory));
        // then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        verify(categoryGateway, times(0)).save(aCategory);
    }

    @Test
    void givenInvalidId_whenCallsSave_shouldReturnError() {
        // given
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'id' should not be empty";
        final var aCategory = Category.with(
                "",
                "Aulas",
                "Conteúdo gravado",
                true,
                InstantUtils.now(),
                InstantUtils.now(),
                null
        );
        // when
        final var actualError = assertThrows(DomainException.class, () -> this.useCase.execute(aCategory));
        // then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        verify(categoryGateway, times(0)).save(aCategory);
    }

    @Test
    void givenInvalidName_whenCallsSave_shouldReturnError() {
        // given
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var aCategory = Category.with(
                UUID.randomUUID().toString().replace("-", ""),
                "",
                "Conteúdo gravado",
                true,
                InstantUtils.now(),
                InstantUtils.now(),
                null
        );
        // when
        final var actualError = assertThrows(DomainException.class, () -> this.useCase.execute(aCategory));
        // then
        assertEquals(expectedErrorCount, actualError.getErrors().size());
        assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
        verify(categoryGateway, times(0)).save(aCategory);
    }
}
