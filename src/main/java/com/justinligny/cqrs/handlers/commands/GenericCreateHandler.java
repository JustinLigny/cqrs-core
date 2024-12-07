package com.justinligny.cqrs.handlers.commands;

import com.justinligny.cqrs.commands.EntityCommand;
import com.justinligny.cqrs.domain.Entity;
import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.interfaces.ICommandHandler;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import com.justinligny.cqrs.outputs.EntityOutput;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericCreateHandler Class</h1> <br>
 *
 *  <p>The {@code GenericCreateHandler} class is an abstract handler designed
 *  for handling create operations. It provides a generic implementation for
 *  handling the creation of entities, including the validation, domain logic
 *  processing, and database persistence.</p> <br>
 *
 *  <p>This class extends {@code GenericCommandHandler} and implements the
 *  {@code ICommandHandler} interface, providing a structure for handling
 *  entity creation commands. It is meant to be inherited and used for
 *  specific entity creation logic.</p> <br>
 *
 *  <p>The {@code handle} method orchestrates the entire process of entity
 *  creation, from validation and domain logic processing to saving the entity
 *  in the database.</p> <br>
 *
 *  @param <TDomain> The domain class representing the business logic entity.
 *  @param <TDbEntity> The database entity class that will be persisted.
 *  @param <TCommand> The command class containing the data for the creation.
 *  @param <TOutput> The output class used for the response after creation.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
public abstract class GenericCreateHandler<
        TDomain extends Entity,
        TDbEntity extends DbEntity,
        TCommand extends EntityCommand,
        TOutput extends EntityOutput>
        extends GenericCommandHandler<TDbEntity, TCommand>
        implements ICommandHandler<TCommand, TOutput> {

    /**
     * The domain class type used for processing the business logic.
     *
     * @since 1.0.1
     */
    private final Class<TDomain> domainClass;

    /**
     * The database entity class type used for saving the entity.
     *
     * @since 1.0.1
     */
    private final Class<TDbEntity> dbEntityClass;

    /**
     * The output class type used to return the result after the entity creation.
     *
     * @since 1.0.1
     */
    private final Class<TOutput> outputClass;

    /**
     * Constructor to initialize the handler with required dependencies.
     *
     * @param modelMapper The {@code ModelMapper} instance used for mapping.
     * @param domainClass The class type of the domain entity.
     * @param dbEntityClass The class type of the database entity.
     * @param repository The repository to handle database interactions.
     * @param outputClass The class type of the output to return.
     *
     * @since 1.0.1
     */
    public GenericCreateHandler(
            ModelMapper modelMapper,
            Class<TDomain> domainClass,
            Class<TDbEntity> dbEntityClass,
            IEntityRepository<TDbEntity> repository,
            Class<TOutput> outputClass) {
        super(repository, modelMapper);
        this.domainClass = domainClass;
        this.dbEntityClass = dbEntityClass;
        this.outputClass = outputClass;
    }

    /**
     * Handles the creation of an entity.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Validates the input command.</li>
     *   <li>Maps the command to a domain entity.</li>
     *   <li>Maps the domain entity to a database entity.</li>
     *   <li>Saves the database entity.</li>
     *   <li>Maps the saved entity to the output format and returns it.</li>
     * </ol>
     *
     * @param input The command containing the entity creation data.
     * @return The output containing the created entity data.
     *
     * @since 1.0.1
     */
    @Override
    public final TOutput handle(TCommand input) {

        beforeValidationProcessing(input);

        if (validator != null)
            validator.validate(input);

        beforeDomainProcessing(input);

        TDomain entityDomain = modelMapper.map(input, domainClass);

        afterDomainCreation(entityDomain);

        TDbEntity entityDatabase = modelMapper.map(entityDomain, dbEntityClass);

        beforeEntitySave(entityDatabase);

        TDbEntity entityDatabaseCreated = repository.save(entityDatabase);

        afterEntitySave(entityDatabaseCreated);

        return modelMapper.map(entityDatabaseCreated, outputClass);
    }

    /**
     * Hook method for processing before the domain logic is applied to the input.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * before the domain entity creation.</p>
     *
     * @param input The command input to be processed.
     *
     * @since 1.0.1
     */
    public void beforeDomainProcessing(TCommand input) {}

    /**
     * Hook method for processing after the domain entity is created.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * after the domain entity is created.</p>
     *
     * @param entityDomain The created domain entity.
     *
     * @since 1.0.1
     */
    public void afterDomainCreation(TDomain entityDomain) {}

    /**
     * Hook method for processing before the database entity is saved.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * before saving the entity to the database.</p>
     *
     * @param entityDatabase The database entity to be saved.
     *
     * @since 1.0.1
     */
    public void beforeEntitySave(TDbEntity entityDatabase) {}

    /**
     * Hook method for processing after the database entity is saved.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * after the entity is saved to the database.</p>
     *
     * @param entityDatabaseCreated The saved database entity.
     *
     * @since 1.0.1
     */
    public void afterEntitySave(TDbEntity entityDatabaseCreated) {}
}
