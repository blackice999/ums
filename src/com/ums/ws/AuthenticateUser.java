package com.ums.ws;

import com.ums.db.DBManager;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by W7 on 06.11.2017.
 */
public class AuthenticateUser extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());

        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("SELECT * FROM users WHERE email = ?");
        preparedStatement.setString(1, json.getString("username"));
        ResultSet resultSet = preparedStatement.executeQuery();

        JSONObject result = new JSONObject();
        JSONArray data = new JSONArray();
        boolean val = resultSet.next();

        if (!val) {
            data.put(result.put("error", "no user found"));
        } else {
            result.put("token", resultSet.getString("id"));
            result.put("role", "student");
        }

        return result;
    }
}
