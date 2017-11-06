package com.ums.ws;

import com.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;

/**
 * Created by W7 on 06.11.2017.
 */
public class AddUser extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {
        JSONObject json = new JSONObject(request.body());

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)");
        preparedStatement.setString(1, json.getString("first_name"));
        preparedStatement.setString(2, json.getString("last_name"));
        preparedStatement.setString(3, json.getString("email"));
        preparedStatement.setString(4, json.getString("password"));
        preparedStatement.execute();

        JSONObject result = new JSONObject();
        result.put("result", "ok");

        return result;
    }
}
