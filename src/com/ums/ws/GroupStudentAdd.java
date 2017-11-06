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
public class GroupStudentAdd extends MyRoute {
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
            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO group_students(groups_id, students_id, semesters_id) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, json.getInt("groups_id"));
            preparedStatement.setInt(2, json.getInt("students_id"));
            preparedStatement.setInt(3, json.getInt("semesters_id"));
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            result.put("id", resultSet.getInt(1));
        }

        return result;

    }
}
