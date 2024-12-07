package com.justinligny.cqrs.interfaces;

/**
 * <h1>ICommandHandler Interface</h1><br>
 *
 * <p>The {@code ICommandHandler} interface defines a generic handler
 * for processing commands. It accepts an input of type {@code I} and
 * returns an output of type {@code O}.</p><br>
 *
 * <p>This interface is typically implemented by command handler classes
 * responsible for executing command logic and returning results.</p><br>
 *
 * @param <I> The input type (command).
 * @param <O> The output type (response).
 *
 * @since 1.0.0
 */
public interface ICommandHandler<I, O> {

    /**
     * Handles the input command and returns the output result.
     *
     * <p>This method is responsible for processing the input command
     * and returning the corresponding output.</p><br>
     *
     * @param input The input command to process.
     * @return The output response resulting from the command.
     *
     * @since 1.0.0
     */
    O handle(I input);
}
