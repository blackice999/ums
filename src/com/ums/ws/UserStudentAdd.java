package com.ums.ws;

import com.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by W7 on 06.11.2017.
 */
public class UserStudentAdd extends MyRoute {
    @Override
    public Object myHandle(Request request, Response response) throws Exception {
        JSONObject json = new JSONObject(request.body());

        PreparedStatement preparedStatementAdmin = DBManager.getConnection().prepareStatement("SELECT * FROM administrators WHERE id = ?");
        preparedStatementAdmin.setInt(1, json.getInt("token"));
        ResultSet resultSetAdmin = preparedStatementAdmin.executeQuery();

        JSONObject result = new JSONObject();
        if (!resultSetAdmin.next()) {
            result.put("error", "The user is not an admin");
        } else {
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, json.getString("first_name"));
            preparedStatement.setString(2, json.getString("last_name"));
            preparedStatement.setString(3, json.getString("username"));
            preparedStatement.setString(4, json.getString("password"));
            preparedStatement.execute();

            PreparedStatement preparedStatement2 = DBManager.getConnection().prepareStatement("INSERT INTO students(gender, birth_date) VALUES(?, ?)");
            preparedStatement2.setString(1, json.getString("gender"));
            preparedStatement2.setString(2, json.getString("birth_date"));
            preparedStatement2.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            result.put("id", resultSet.getInt(1));
        }

        return result;
    }
}
