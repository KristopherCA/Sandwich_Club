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
            JSONObject sandwichObject = new JSONObject(json);

            //get JSON objects from JSON data
            JSONObject sandwichName = sandwichObject.optJSONObject("name");

            //get JSON string from JSON data
             String sandwichMainName = sandwichName.optString("mainName");

            //get JSON Array and convert JSON array to java list
            JSONArray alsoknownasArray = sandwichName.optJSONArray("alsoKnownAs");
            List<String> sandwichAlsoKnownAs = convertArray(alsoknownasArray);


            //get rest of JSON strings from JSON data
            String sandwichPlaceOfOrigin = sandwichObject.optString("placeOfOrigin");
            String sandwichDescription = sandwichObject.optString("description");
            String sandwichImage = sandwichObject.optString("image");

            //get JSON Array and convert JSON array to java list
            JSONArray ingredientsArray = sandwichObject.optJSONArray("ingredients");
            List<String> sandwichIngredients = convertArray(ingredientsArray);

            //create a new sandwhich object to return JSON data
            sandwich = new Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, sandwichIngredients);


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
