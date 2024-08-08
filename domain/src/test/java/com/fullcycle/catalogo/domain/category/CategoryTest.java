package com.fullcycle.catalogo.domain.category;


import com.fullcycle.catalogo.domain.UnitTest;
import com.fullcycle.catalogo.domain.exceptions.DomainException;
import com.fullcycle.catalogo.domain.utils.InstantUtils;
import com.fullcycle.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CategoryTest extends UnitTest {


    @Test
    void givenAValidParams_whenCallWith_thenInstantiateACategory() {
        // given
        final var expectedID = UUID.randomUUID().toString();
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedDates = InstantUtils.now();

        // when
        final var actualCategory =
                Category.with(expectedID, expectedName, expectedDescription, expectedIsActive, expectedDates, expectedDates, null);

        // then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(expectedID, actualCategory.id());
        Assertions.assertEquals(expectedName, actualCategory.name());
        Assertions.assertEquals(expectedDescription, actualCategory.description());
        Assertions.assertEquals(expectedIsActive, actualCategory.active());
        Assertions.assertEquals(expectedDates, actualCategory.createdAt());
        Assertions.assertEquals(expectedDates, actualCategory.updatedAt());
        Assertions.assertNull(actualCategory.deletedAt());
    }

    @Test
    void givenAValidParams_whenCallWithCategory_thenInstantiateACategory() {
        // given
        final var expectedID = UUID.randomUUID().toString();
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedDates = InstantUtils.now();
        final var aCategory =
                Category.with(expectedID, expectedName, expectedDescription, expectedIsActive, expectedDates, expectedDates, null);
        // when
        final var actualCategory = Category.with(aCategory);

        // then
        Assertions.assertEquals(aCategory.id(), actualCategory.id());
        Assertions.assertEquals(aCategory.name(), actualCategory.name());
        Assertions.assertEquals(aCategory.description(), actualCategory.description());
        Assertions.assertEquals(aCategory.active(), actualCategory.active());
        Assertions.assertEquals(aCategory.createdAt(), actualCategory.createdAt());
        Assertions.assertEquals(aCategory.updatedAt(), actualCategory.updatedAt());
        Assertions.assertEquals(aCategory.deletedAt(), actualCategory.deletedAt());
    }

    @Test
    void givenAnInvalidNullName_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final String expectedName = null;
        final var expectedID = UUID.randomUUID().toString();
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedDates = InstantUtils.now();
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        // when
        final var actualCategory =
                Category.with(expectedID, expectedName, expectedDescription, expectedIsActive, expectedDates, expectedDates, null);
        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInvalidNullId_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final String expectedID = null;
        final var expectedName = "Filmes";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'id' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedDates = InstantUtils.now();

        final var actualCategory =
                Category.with(expectedID, expectedName, expectedDescription, expectedIsActive, expectedDates, expectedDates, null);

        // when
        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    void givenAnInvalidEmptyName_whenCallNewCategory_thenShouldReceiveError() {
        // given
        final var expectedID = UUID.randomUUID().toString();
        final var expectedName = "  ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedDates = InstantUtils.now();

        final var actualCategory =
                Category.with(expectedID, expectedName, expectedDescription, expectedIsActive, expectedDates, expectedDates, null);

        // when
        final var actualException =
                Assertions.assertThrows(DomainException.class, () -> actualCategory.validate(new ThrowsValidationHandler()));

        // then
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

}