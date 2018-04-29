package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            //create JSON Sandwich object to get JSON data
            JSONObject swObject = new JSONObject(json);

            //get JSON objects from JSON data
            JSONObject swName = swObject.getJSONObject("name");

            //get JSON string from JSON data
             String swMainName = swName.getString("mainName");

            //get JSON Array and convert JSON array to java list
            JSONArray akaArray = swName.getJSONArray("alsoKnownAs");
            List<String> swAKA = convertArray(akaArray);

            //get rest of JSON strings from JSON data
            String swPlaceOfOrigin = swObject.getString("placeOfOrigin");
            String swDescription = swObject.getString("description");
            String swImage = swObject.getString("image");

            //get JSON Array and convert JSON array to java list
            JSONArray ingredientsArray = swObject.getJSONArray("ingredients");
            List<String> swIngredients = convertArray(ingredientsArray);

            //create a new sandwhich object to return JSON data
            sandwich = new Sandwich(swMainName, swAKA, swPlaceOfOrigin, swDescription, swImage, swIngredients);


        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        //return newly created sandwhich object
        return sandwich;

    }
        //create helper method for convert arrays
        private static List<String> convertArray(JSONArray jsonArray) throws JSONException {
            int arrayLength = jsonArray.length();
            List<String> convertedArray = new ArrayList<>();

            // iterate through array and  add to list
            for (int i = 0; i < arrayLength; i++) {
                convertedArray.add(jsonArray.getString(i));

            }

            return convertedArray;
        }

    }
