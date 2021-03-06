/*
 * Copyright 2012 Atteo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.atteo.moonshine.jpa;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

public abstract class DelegatingEntityManager implements EntityManager {
	protected abstract EntityManager getEntityManager();

	@Override
	public void persist(Object entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public <T> T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void remove(Object entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey) {
		return getEntityManager().find(entityClass, primaryKey);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties) {
		return getEntityManager().find(entityClass, primaryKey, properties);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode) {
		return getEntityManager().find(entityClass, primaryKey, lockMode);
	}

	@Override
	public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties) {
		return getEntityManager().find(entityClass, primaryKey, lockMode, properties);
	}

	@Override
	public <T> T getReference(Class<T> entityClass, Object primaryKey) {
		return getEntityManager().getReference(entityClass, primaryKey);
	}

	@Override
	public void flush() {
		getEntityManager().flush();
	}

	@Override
	public void setFlushMode(FlushModeType flushMode) {
		getEntityManager().setFlushMode(flushMode);
	}

	@Override
	public FlushModeType getFlushMode() {
		return getEntityManager().getFlushMode();
	}

	@Override
	public void lock(Object entity, LockModeType lockMode) {
		getEntityManager().lock(entity, lockMode);
	}

	@Override
	public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().lock(entity, lockMode, properties);
	}

	@Override
	public void refresh(Object entity) {
		getEntityManager().refresh(entity);
	}

	@Override
	public void refresh(Object entity, Map<String, Object> properties) {
		getEntityManager().refresh(entity, properties);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode) {
		getEntityManager().refresh(entity, lockMode);
	}

	@Override
	public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties) {
		getEntityManager().refresh(entity, lockMode, properties);
	}

	@Override
	public void clear() {
		getEntityManager().clear();
	}

	@Override
	public void detach(Object entity) {
		getEntityManager().detach(entity);
	}

	@Override
	public boolean contains(Object entity) {
		return getEntityManager().contains(entity);
	}

	@Override
	public LockModeType getLockMode(Object entity) {
		return getEntityManager().getLockMode(entity);
	}

	@Override
	public void setProperty(String propertyName, Object value) {
		getEntityManager().setProperty(propertyName, value);
	}

	@Override
	public Map<String, Object> getProperties() {
		return getEntityManager().getProperties();
	}

	@Override
	public Query createQuery(String qlString) {
		return getEntityManager().createQuery(qlString);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery) {
		return getEntityManager().createQuery(criteriaQuery);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass) {
		return getEntityManager().createQuery(qlString, resultClass);
	}

	@Override
	public Query createNamedQuery(String name) {
		return getEntityManager().createNamedQuery(name);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass) {
		return getEntityManager().createNamedQuery(name, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString) {
		return getEntityManager().createNativeQuery(sqlString);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Query createNativeQuery(String sqlString, Class resultClass) {
		return getEntityManager().createNativeQuery(sqlString, resultClass);
	}

	@Override
	public Query createNativeQuery(String sqlString, String resultSetMapping) {
		return getEntityManager().createNativeQuery(sqlString, resultSetMapping);
	}

	@Override
	public void joinTransaction() {
		getEntityManager().joinTransaction();
	}

	@Override
	public <T> T unwrap(Class<T> cls) {
		return getEntityManager().unwrap(cls);
	}

	@Override
	public Object getDelegate() {
		return getEntityManager().getDelegate();
	}

	@Override
	public void close() {
		getEntityManager().close();
	}

	@Override
	public boolean isOpen() {
		return getEntityManager().isOpen();
	}

	@Override
	public EntityTransaction getTransaction() {
		return getEntityManager().getTransaction();
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return getEntityManager().getEntityManagerFactory();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	@Override
	public Metamodel getMetamodel() {
		return getEntityManager().getMetamodel();
	}
}
