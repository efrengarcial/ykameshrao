package com.yourpackagename.framework.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/
 * @author egarcia
 *
 * @param <T>
 */
public abstract class AbstractJpaDAO< T extends Serializable > {

	private Class< T > clazz;

	@PersistenceContext
	EntityManager entityManager;

	public void setClazz( final Class< T > clazzToSet ){
		this.clazz = clazzToSet;
	}

	public T getById( final Long id ){
		return this.entityManager.find( this.clazz, id );
	}
	@SuppressWarnings("unchecked")
	public List< T > getAll(){
		return this.entityManager.createQuery( "from " + this.clazz.getName() )
				.getResultList();
	}

	public void create( final T entity ){
		this.entityManager.persist( entity );
	}

	public void update( final T entity ){
		this.entityManager.merge( entity );
	}

	public void delete( final T entity ){
		this.entityManager.remove( entity );
	}
	public void deleteById( final Long entityId ){
		final T entity = this.getById( entityId );

		this.delete( entity );
	}
}
