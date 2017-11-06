package com.ums.ws;

import com.ums.db.DBManager;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by W7 on 06.11.2017.
 */
public class ListUsers extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        JSONArray users = new JSONArray();
        while (resultSet.next()) {
            JSONObject user = new JSONObject();

            user.put("id", resultSet.getInt("id"));
            user.put("first_name", resultSet.getString("first_name"));
            user.put("last_name", resultSet.getString("last_name"));
            user.put("email", resultSet.getString("email"));
            user.put("password", resultSet.getString("password"));

            users.put(user);
        }

        return users;
    }
}
