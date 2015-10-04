package com.example.jola.zadanie;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Jola on 10/3/2015.
 */
public class DbConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {DbItem.class,};

    public static void main(String[] args){
        try {
            writeConfigFile("ormlite_config.txt", classes);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
