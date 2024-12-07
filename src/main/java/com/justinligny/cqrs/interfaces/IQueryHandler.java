package com.justinligny.cqrs.interfaces;

/**
 *  <h1>IQueryHandler Interface</h1> <br>
 *
 *  <p>The {@code IQueryHandler} interface defines a generic handler for
 *  query operations. It is intended to be implemented by classes that
 *  process queries, taking an input of type {@code I} and returning an
 *  output of type {@code O}.</p> <br>
 *
 *  <p>The {@code handle} method is responsible for processing the input
 *  query and returning the corresponding output.</p> <br>
 *
 *  @param <I> The input type (query).
 *  @param <O> The output type (response).
 *
 *  @since 1.0.1
 */
public interface IQueryHandler<I, O> {

    /**
     * Handles the query and returns the result.
     *
     * <p>This method processes the input query and returns the corresponding
     * output. The exact implementation depends on the specific query
     * being handled.</p>
     *
     * @param input The input query to be processed.
     * @return The output response resulting from the query.
     *
     * @since 1.0.1
     */
    O handle(I input);
}

