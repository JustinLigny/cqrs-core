package com.justinligny.cqrs.handlers.commands;

import com.justinligny.cqrs.commands.EntityCommand;
import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import com.justinligny.cqrs.interfaces.IValidator;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericCommandHandler Class</h1> <br>
 *
 *  <p>The {@code GenericCommandHandler} class is an abstract base class
 *  that handles commands in the application. It is designed to be extended
 *  by specific command handler classes, which implement the business logic
 *  for processing commands related to entities.</p> <br>
 *
 *  <p>This class is not meant to be used directly but rather as a foundation
 *  for other handlers. It is responsible for handling common logic, such as
 *  repository access and validation setup. The validation is done by an
 *  injected {@code IValidator} implementation.</p> <br>
 *
 *  <p>The constructor initializes the repository and model mapper, and the
 *  {@code setValidator} method allows setting a validator for the command
 *  to be processed.</p> <br>
 *
 *  @param <TDbEntity> The type of the database entity, extending {@code DbEntity}.
 *  @param <TCommand> The type of the command to be processed.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
public abstract class GenericCommandHandler<
        TDbEntity extends DbEntity,
        TCommand extends EntityCommand> {

    /**
     * The repository used to interact with the database entities.
     *
     * @since 1.0.1
     */
    protected final IEntityRepository<TDbEntity> repository;

    /**
     * The {@code ModelMapper} instance used to map entities to DTOs and vice versa.
     *
     * @since 1.0.1
     */
    protected final ModelMapper modelMapper;

    /**
     * The validator used for validating the command before processing.
     *
     * @since 1.0.1
     */
    protected IValidator<TCommand> validator;

    /**
     * Constructor to initialize the repository and modelMapper for the command handler.
     *
     * @param repository The repository used to interact with the entities.
     * @param modelMapper The {@code ModelMapper} instance for mapping.
     *
     * @since 1.0.1
     */
    public GenericCommandHandler(IEntityRepository<TDbEntity> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    /**
     * Sets the validator for the command to be processed.
     *
     * @param validator The validator used to validate the command input.
     *
     * @since 1.0.1
     */
    public final void setValidator(IValidator<TCommand> validator) {
        this.validator = validator;
    }

    /**
     * Hook method to perform operations before the validation of the command.
     *
     * <p>This method can be overridden by subclasses to implement logic that
     * should occur before validating the command input.</p>
     *
     * @param input The command input to be validated.
     *
     * @since 1.0.1
     */
    public void beforeValidationProcessing(TCommand input) {}
}
