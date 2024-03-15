package org.w3.api;

import org.json.JSONArray;
import org.w3.controller.WeatherFetcher;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

import static spark.Spark.*;

public class WeatherDBAPI {

    private static String getJSONdata(Request request, Response response) throws SQLException {
        JSONArray recordList=new JSONArray();
        recordList= WeatherFetcher.getRecordFromDB();
        response.type("application/json");
        return recordList.toString();
    }

    public static void main(String[] args) {

        port(8080); // Set port
        // Enable CORS for all origins, methods, and headers
         before((req, res) -> {
             res.header("Access-Control-Allow-Origin", "*");
             res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
             res.header("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept");
             res.header("Access-Control-Allow-Credentials", "true");
         });
         // Define route to fetch weather data
         get("/weather", ((request, response) -> getJSONdata(request, response) ));

    }


}
