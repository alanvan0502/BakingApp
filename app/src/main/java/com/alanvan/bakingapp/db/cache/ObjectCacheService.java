package com.alanvan.bakingapp.db.cache;

import java.util.Collection;

public interface ObjectCacheService {

    <T extends CachableEntity> T loadEntityById(Object var1, Class<T> var2);

    <T extends CachableEntity> void storeEntity(T var1);

    <T extends CachableEntity> void deleteEntityById(int id, Class<T> entityClass);

    <T extends CachableEntity> void storeEntities(Collection<T> var1);

    <T extends CachableEntity> void deleteEntitiesByIds(Collection ids, Class<T> entityClass);

    void beginTransaction();

    void endTransaction();
}
