package com.fullcycle.catalogo.domain.exceptions;

import java.util.List;
import com.fullcycle.catalogo.domain.validation.Error;

public class DomainException extends NoStacktraceException {

    protected final List<Error> errors;

    protected DomainException(final String aMessage, final List<Error> errors) {
        super(aMessage);
        this.errors = errors;
    }

    public static DomainException with(final Error anError) {
        return new DomainException(anError.message(), List.of(anError));
    }

    public static DomainException with(final List<Error> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
