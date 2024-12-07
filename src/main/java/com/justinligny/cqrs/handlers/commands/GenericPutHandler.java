package com.justinligny.cqrs.handlers.commands;

import com.justinligny.cqrs.commands.EntityCommand;
import com.justinligny.cqrs.domain.Entity;
import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.exceptions.EntityNotFoundException;
import com.justinligny.cqrs.interfaces.ICommandHandler;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import com.justinligny.cqrs.outputs.EntityOutput;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericPutHandler Class</h1> <br>
 *
 *  <p>The {@code GenericPutHandler} class is an abstract handler designed
 *  for handling update (PUT) operations for entities. It provides a generic
 *  implementation for updating entities in the database.</p> <br>
 *
 *  <p>This class extends {@code GenericCommandHandler} and implements the
 *  {@code ICommandHandler} interface. It offers a structure for handling
 *  entity update commands. It is meant to be inherited and customized for
 *  specific entity update logic.</p> <br>
 *
 *  <p>The {@code handle} method orchestrates the update process for an entity,
 *  including validation, mapping input data to domain objects, and updating
 *  the database record.</p> <br>
 *
 *  @param <TDomain> The domain entity class to update.
 *  @param <TDbEntity> The database entity class to update.
 *  @param <TCommand> The command class containing the entity update details.
 *  @param <TOutput> The output class used to return the updated entity.
 *
 *  @since 1.0.1
 */
public abstract class GenericPutHandler<
        TDomain extends Entity,
        TDbEntity extends DbEntity,
        TCommand extends EntityCommand,
        TOutput extends EntityOutput>
        extends GenericCommandHandler<TDbEntity, TCommand>
        implements ICommandHandler<TCommand, TOutput> {

    /**
     * The domain class to map the entity data to.
     */
    private final Class<TDomain> domainClass;

    /**
     * The output class to return after updating the entity.
     */
    private final Class<TOutput> outputClass;

    /**
     * Constructor to initialize the handler with required dependencies.
     *
     * @param modelMapper The {@code ModelMapper} instance used for mapping.
     * @param domainClass The domain entity class for the update.
     * @param repository The repository to handle database interactions.
     * @param outputClass The output class for the response.
     *
     * @since 1.0.1
     */
    public GenericPutHandler(
            ModelMapper modelMapper,
            Class<TDomain> domainClass,
            IEntityRepository<TDbEntity> repository,
            Class<TOutput> outputClass) {
        super(repository, modelMapper);
        this.domainClass = domainClass;
        this.outputClass = outputClass;
    }

    /**
     * Handles the entity update process.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Validates the input command.</li>
     *   <li>Retrieves the existing entity from the database.</li>
     *   <li>Maps the input data to the domain entity.</li>
     *   <li>Updates the entity in the database.</li>
     * </ol>
     *
     * @param input The command containing the entity update details.
     * @return The updated entity mapped to the output class.
     *
     * @since 1.0.1
     */
    @Override
    public final TOutput handle(TCommand input) {

        beforeValidationProcessing(input);

        if(validator != null)
            validator.validate(input);

        TDbEntity entityDatabaseExisting = repository.findById(input.id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID" + input.id));

        beforeDomainProcessing(input);

        TDomain entityDomain = modelMapper.map(entityDatabaseExisting, domainClass);
        modelMapper.map(input, entityDomain);

        afterDomainUpdate(entityDomain);

        modelMapper.map(entityDomain, entityDatabaseExisting);

        beforeEntityUpdate(entityDatabaseExisting);

        TDbEntity entityDatabaseUpdated = repository.save(entityDatabaseExisting);

        afterEntityUpdate(entityDatabaseUpdated);

        return modelMapper.map(entityDatabaseUpdated, outputClass);
    }

    /**
     * Hook method for processing before the domain object is updated.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * before the domain object is updated.</p>
     *
     * @param input The command containing the update data.
     *
     * @since 1.0.1
     */
    public void beforeDomainProcessing(TCommand input) {}

    /**
     * Hook method for processing after the domain object is updated.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * after the domain object has been updated.</p>
     *
     * @param entityDomainUpdated The updated domain entity.
     *
     * @since 1.0.1
     */
    public void afterDomainUpdate(TDomain entityDomainUpdated) {}

    /**
     * Hook method for processing before the database entity is updated.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * before the database entity is updated.</p>
     *
     * @param entityDatabase The entity to be updated.
     *
     * @since 1.0.1
     */
    public void beforeEntityUpdate(TDbEntity entityDatabase) {}

    /**
     * Hook method for processing after the database entity is updated.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * after the database entity has been updated.</p>
     *
     * @param entityDatabaseUpdated The updated database entity.
     *
     * @since 1.0.1
     */
    public void afterEntityUpdate(TDbEntity entityDatabaseUpdated) {}
}
