package com.maroc_air.Modelles;
import com.maroc_air.DAO.Dao;
import com.maroc_air.DAO.TableTest;
import com.maroc_air.Database.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
abstract public class Model extends Connection {
    protected static Dao getRepository(Model instance) {
        return Dao.getRepository(instance.getTableName());
    }

    public String getTableName() {
        TableTest tableTest = getClass().getAnnotation(TableTest.class);
        if (tableTest != null) {
            return tableTest.tableName();
        }
        return null;
    }

}
