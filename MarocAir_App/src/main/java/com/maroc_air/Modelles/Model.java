package com.maroc_air.Modelles;
import com.maroc_air.Database.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
abstract public class Model extends Connection {
   /* protected static Dao getRepository(Model instance) {
        return Dao.getRepository(instance.getTableName());
    }*/
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
