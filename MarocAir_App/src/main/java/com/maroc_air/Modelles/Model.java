package com.maroc_air.Modelles;
import com.maroc_air.DAO.DAO;
import com.maroc_air.Database.DBConnection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
abstract public class Model extends DBConnection {
    protected static DAO getRepository(Model instance) {
        return DAO.getRepository(instance.getTableName());
    }
    public static int getSize(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            if (size > 0) {
                resultSet.beforeFirst();
            }
        }catch (SQLException e) {
            System.out.println("Error while getting size of result set");
            e.printStackTrace();
        }
        return size;
    }
    protected String getPrimaryKey() {
        return "id";
    }
    public String getTableName() {
        TableTest tableTest = getClass().getAnnotation(TableTest.class);
        if (tableTest != null) {
            return tableTest.tableName();
        }
        return null;
    }

}
