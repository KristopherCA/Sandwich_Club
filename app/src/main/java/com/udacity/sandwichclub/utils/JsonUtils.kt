package com.udacity.sandwichclub.utils

import com.udacity.sandwichclub.model.Sandwich

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

object JsonUtils {

    fun parseSandwichJson(json: String): Sandwich? {

        var sandwich: Sandwich? = null

        try {
            //create JSON Sandwich object to get JSON data
            val sandwichObject = JSONObject(json)

            //get JSON objects from JSON data
            val sandwichName = sandwichObject.optJSONObject("name")

            //get JSON string from JSON data
            val sandwichMainName = sandwichName.optString("mainName")

            //get JSON Array and convert JSON array to java list
            val alsoknownasArray = sandwichName.optJSONArray("alsoKnownAs")
            val sandwichAlsoKnownAs = convertArray(alsoknownasArray)


            //get rest of JSON strings from JSON data
            val sandwichPlaceOfOrigin = sandwichObject.optString("placeOfOrigin")
            val sandwichDescription = sandwichObject.optString("description")
            val sandwichImage = sandwichObject.optString("image")

            //get JSON Array and convert JSON array to java list
            val ingredientsArray = sandwichObject.optJSONArray("ingredients")
            val sandwichIngredients = convertArray(ingredientsArray)

            //create a new sandwhich object to return JSON data
            sandwich = Sandwich(sandwichMainName, sandwichAlsoKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, sandwichIngredients)


        } catch (ex: JSONException) {
            ex.printStackTrace()
        }

        //return newly created sandwich object
        return sandwich

    }

    //create helper method for convert arrays
    @Throws(JSONException::class)
    private fun convertArray(jsonArray: JSONArray): List<String> {
        val arrayLength = jsonArray.length()
        val convertedArray = ArrayList<String>()

        // iterate through array and  add to list
        for (i in 0 until arrayLength) {
            convertedArray.add(jsonArray.getString(i))

        }

        return convertedArray
    }

}
