package com.alanvan.bakingapp.db.cache;

import android.database.sqlite.SQLiteDatabase;

import com.alanvan.bakingapp.db.DbHelper;
import com.alanvan.bakingapp.injection.Injector;
import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

public class CacheServiceImpl implements ObjectCacheService {

    private static CacheServiceImpl sInstance;

    protected SQLiteDatabase db;

    DbHelper dbHelper;

    synchronized public static CacheServiceImpl getInstance() {
        if (sInstance == null) {
            sInstance = new CacheServiceImpl();
        }
        return sInstance;
    }

    protected CacheServiceImpl() {
        dbHelper = DbHelper.getInstance(Injector.getContextComponent().appContext());
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public <T extends CachableEntity> T loadEntityById(Object id, Class<T> entityClass) {
        T entity = null;
        try {
            Dao<T, Object> dao = dbHelper.getDao(entityClass);
            entity = dao.queryForId(id);
        } catch (SQLException e) {
            Logger.e(e.getMessage());
        }
        return entity;
    }

    @Override
    public <T extends CachableEntity> void storeEntity(T entity) {
        try {
            Dao<T, Object> dao = (Dao<T, Object>) dbHelper.getDao(((Object) entity).getClass());
            dao.createOrUpdate(entity);
        } catch (SQLException e) {
            Logger.e(e.getMessage());
        }
    }

    @Override
    public <T extends CachableEntity> void storeEntities(Collection<T> listOfEntities) {
        if (listOfEntities == null || listOfEntities.size() <= 0) {
            return;
        }
        try {
            Iterator<T> iter = listOfEntities.iterator();
            Class clss = iter.next().getClass();
            final Dao<T, Object> dao = dbHelper.getDao(clss);

            dao.callBatchTasks((Callable<Void>) () -> {
                for (T entity : listOfEntities) {
                    dao.createOrUpdate(entity);
                }
                return null;
            });
        } catch (SQLException e) {
            Logger.e(e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            Logger.e(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    public <T extends CachableEntity> void deleteEntityById(int id, Class<T> entityClass) {
        try {
            Dao<T, Object> dao = dbHelper.getDao(entityClass);
            dao.deleteById(id);
        } catch (SQLException e) {
            Logger.e(e.getMessage());
        }
    }

    @Override
    public <T extends CachableEntity> void deleteEntitiesByIds(final Collection ids, Class<T> entityClass) {
        if (ids == null || ids.size() <= 0) {
            return;
        }
        try {
            final Dao<T, Object> dao = dbHelper.getDao(entityClass);

            dao.callBatchTasks((Callable<Void>) () -> {
                for (Object id : ids) {
                    dao.deleteById(id);
                }
                return null;
            });
        } catch (SQLException e) {
            Logger.e(e.getMessage());
        } catch (Exception e) {
            Logger.e(e.getMessage());
        }
    }

    @Override
    public void beginTransaction() {
        db = dbHelper.getWritableDatabase();
        db.beginTransaction();
    }

    @Override
    public void endTransaction() {
        db = dbHelper.getWritableDatabase();
        db.endTransaction();
    }
}
