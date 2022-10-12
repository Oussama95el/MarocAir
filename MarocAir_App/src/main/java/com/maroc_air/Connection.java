package com.maroc_air;

import com.maroc_air.Database.DBConnection;

public abstract class Connection {
    public String table;
    protected DBConnection db = new DBConnection();
}
