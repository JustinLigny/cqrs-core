package com.justinligny.cqrs.handlers.commands;


import com.justinligny.cqrs.commands.EntityCommand;
import com.justinligny.cqrs.entities.DbEntity;
import com.justinligny.cqrs.exceptions.EntityNotFoundException;
import com.justinligny.cqrs.interfaces.ICommandWithoutResponseHandler;
import com.justinligny.cqrs.interfaces.IEntityRepository;
import org.modelmapper.ModelMapper;

/**
 *  <h1>GenericDeleteHandler Class</h1> <br>
 *
 *  <p>The {@code GenericDeleteHandler} class is an abstract handler designed
 *  for handling delete operations. It provides a generic implementation for
 *  deleting entities from the database.</p> <br>
 *
 *  <p>This class extends {@code GenericCommandHandler} and implements the
 *  {@code ICommandWithoutResponseHandler} interface. It provides a structure
 *  for handling entity deletion commands. It is meant to be inherited and
 *  used for specific entity deletion logic.</p> <br>
 *
 *  <p>The {@code handle} method orchestrates the deletion process of an entity,
 *  including validation and the actual deletion from the database.</p> <br>
 *
 *  @param <TDbEntity> The database entity class to be deleted.
 *  @param <TCommand> The command class containing the entity ID to delete.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
public abstract class GenericDeleteHandler<
        TDbEntity extends DbEntity,
        TCommand extends EntityCommand>
        extends GenericCommandHandler<TDbEntity, TCommand>
        implements ICommandWithoutResponseHandler<TCommand> {

    /**
     * Constructor to initialize the handler with required dependencies.
     *
     * @param modelMapper The {@code ModelMapper} instance used for mapping.
     * @param repository The repository to handle database interactions.
     *
     * @since 1.0.1
     */
    public GenericDeleteHandler(
            ModelMapper modelMapper,
            IEntityRepository<TDbEntity> repository) {
        super(repository, modelMapper);
    }

    /**
     * Handles the deletion of an entity.
     *
     * <p>This method performs the following steps:</p>
     * <ol>
     *   <li>Validates the input command.</li>
     *   <li>Retrieves the entity from the database using the provided ID.</li>
     *   <li>Deletes the entity from the database.</li>
     * </ol>
     *
     * @param input The command containing the entity ID to delete.
     *
     * @since 1.0.1
     */
    @Override
    public final void handle(TCommand input) {

        beforeValidationProcessing(input);

        if(validator != null)
            validator.validate(input);

        TDbEntity entityDatabase = repository.findById(input.id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with ID" + input.id));

        beforeEntityDelete(entityDatabase);

        repository.delete(entityDatabase);
    }

    /**
     * Hook method for processing before the entity is deleted.
     *
     * <p>This method can be overridden by subclasses to implement logic
     * before the entity is deleted from the database.</p>
     *
     * @param entityDatabase The entity to be deleted.
     *
     * @since 1.0.1
     */
    public void beforeEntityDelete(TDbEntity entityDatabase){}
}
