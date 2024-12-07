package com.justinligny.cqrs.interfaces;

/**
 *  <h1>IValidator Interface</h1> <br>
 *
 *  <p>The {@code IValidator} interface defines a contract for validating input
 *  of a specific type. It provides a method to validate an object of type {@code I}.
 *  This interface is useful when creating generic validation logic that can be
 *  applied to various types of objects.</p> <br>
 *
 *  <p>The {@code validate} method is expected to perform the necessary validation
 *  checks on the input. The specific validation logic will be implemented by the
 *  classes that implement this interface.</p> <br>
 *
 *  @param <I> The type of input that needs to be validated
 *
 * @author Justin Ligny
 * @since 1.0.1
 */
public interface IValidator<I> {

    /**
     * Validates the provided input.
     *
     * @param input The object to validate. This can be any type {@code I}. The
     *        specific validation checks depend on the implementing class.
     *
     * @since 1.0.1
     */
    void validate(I input);
}
