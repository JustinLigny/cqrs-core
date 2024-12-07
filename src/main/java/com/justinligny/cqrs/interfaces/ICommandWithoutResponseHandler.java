package com.justinligny.cqrs.interfaces;

/**
 * <h1>ICommandWithoutResponseHandler Interface</h1><br>
 *
 * <p>The {@code ICommandWithoutResponseHandler} interface defines a
 * handler for commands that do not produce a response. It accepts an
 * input of type {@code I} and does not return any result.</p><br>
 *
 * <p>This interface is typically implemented by command handler classes
 * responsible for executing actions that do not require returning a value.</p><br>
 *
 * @param <I> The input type (command).
 *
 * @since 1.0.0
 */
public interface ICommandWithoutResponseHandler<I> {

    /**
     * Handles the input command without returning a response.
     *
     * <p>This method processes the input command but does not return
     * any output or result.</p><br>
     *
     * @param input The input command to process.
     *
     * @since 1.0.0
     */
    void handle(I input);
}

