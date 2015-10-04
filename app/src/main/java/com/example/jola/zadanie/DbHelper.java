package com.example.jola.zadanie;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Jola on 10/3/2015.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "links.db";
    private static final int DATABASE_VERSION = 1;

    Dao<DbItem, Integer> dao = null;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, DbItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, DbItem.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<DbItem, Integer> getDao(){
        if(dao == null){
            try {
                dao = getDao(DbItem.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dao;
    }
}
