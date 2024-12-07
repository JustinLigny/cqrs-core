package com.justinligny.cqrs.interfaces;

import com.justinligny.cqrs.entities.DbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *  <h1>IEntityRepository Interface</h1> <br>
 *
 *  <p>The {@code IEntityRepository} interface extends both {@code JpaRepository} and
 *  {@code JpaSpecificationExecutor}, providing a generic repository interface for managing
 *  entities of type {@code TDbEntity} that extends {@code DbEntity}. t is designed to be
 *  used in combination with Spring Data JPA, allowing for CRUD (Create, Read, Update,
 *  Delete) operations as well as complex queries using specifications.</p> <br>
 *
 *  <p>This interface inherits the common functionality of {@code JpaRepository}, such
 *  as saving, deleting, and finding entities, and the ability to execute queries defined
 *  by specifications via {@code JpaSpecificationExecutor}.</p> <br>
 *
 *  <p>The type parameter {@code TDbEntity} is a subclass of {@code DbEntity}, and it
 *  represents the entity type for which the repository is responsible.</p> <br>
 *
 *  @param <TDbEntity> The type of entity that extends {@code DbEntity}. It is the entity
 *         that this repository manages.
 *
 *  @author Justin Ligny
 *  @since 1.0.1
 */
@NoRepositoryBean
public interface IEntityRepository<TDbEntity extends DbEntity>
        extends JpaRepository<TDbEntity, Long>, JpaSpecificationExecutor<TDbEntity> {
}
