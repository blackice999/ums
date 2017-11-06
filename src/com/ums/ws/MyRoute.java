package com.ums.ws;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by W7 on 06.11.2017.
 */
public abstract class MyRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Object result;

        try {
            result = myHandle(request, response);
        } catch (Exception e) {
            result = e;
        }

        return result;
    }

    public abstract Object myHandle(Request request, Response response) throws Exception;
}
