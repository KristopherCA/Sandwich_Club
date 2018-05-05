package com.udacity.sandwichclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.squareup.picasso.Picasso
import com.udacity.sandwichclub.model.Sandwich
import com.udacity.sandwichclub.utils.JsonUtils

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val ingredientsIv = findViewById<ImageView>(R.id.image_iv)

        val intent = intent
        if (intent == null) {
            closeOnError()
        }

        val position = intent!!.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION)
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError()
            return
        }

        val sandwiches = resources.getStringArray(R.array.sandwich_details)
        val json = sandwiches[position]
        val sandwich = JsonUtils.parseSandwichJson(json)
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError()
            return
        }

        populateUI(sandwich)
        Picasso.with(this)
                .load(sandwich.image)
                .placeholder(R.drawable.no_image_available)
                .error(R.drawable.no_image_available)
                .into(ingredientsIv)


        title = sandwich.mainName
    }

    private fun closeOnError() {
        finish()
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show()
    }

    private fun populateUI(sandwich: Sandwich) {


        //find place of origin view id, get the origin from sandwich object  and set the text
        val originTV = findViewById<TextView>(R.id.origin_tv)
        var placeofOrigin: String? = sandwich.placeOfOrigin
        if (placeofOrigin == null || placeofOrigin == "") {
            placeofOrigin = getString(R.string.origin_error_message)
        }
        originTV.text = placeofOrigin

        //find description view id, get the description from sandwich object and set the text
        val descriptionTV = findViewById<TextView>(R.id.description_tv)
        val description = sandwich.description
        descriptionTV.text = description

        //find ingredients view id, get the ingredients list from sandwich object and set the text
        val ingredientsTV = findViewById<TextView>(R.id.ingredients_tv)
        val ingredients: String
        val ingredientsList = sandwich.ingredients!!
        if (ingredientsList.isEmpty()) {
            ingredients = getString(R.string.ingredients_error_message)
        } else {
            val builder = StringBuilder()
            for (i in ingredientsList.indices) {
                builder.append(ingredientsList[i])
                // add line break until last item is received from the list
                if (i != ingredientsList.size - 1)
                    builder.append('\n')
            }
            ingredients = builder.toString()
        }
        ingredientsTV.text = ingredients

        //find also known as view id, get the also known as list from sandwich object and set the text
        val alsoknownasTV = findViewById<TextView>(R.id.also_known_tv)
        val alsoKnownAs: String
        val alsoKnownAsList = sandwich.alsoKnownAs!!
        if (alsoKnownAsList.isEmpty()) {
            alsoKnownAs = getString(R.string.alsoknownas_error_message)
        } else {
            val builder = StringBuilder()
            for (i in alsoKnownAsList.indices) {
                builder.append(alsoKnownAsList[i])
                // add line break until last item is received from the list
                if (i != alsoKnownAsList.size - 1)
                    builder.append('\n')
            }
            alsoKnownAs = builder.toString()
        }
        alsoknownasTV.text = alsoKnownAs

    }

    companion object {

        val EXTRA_POSITION = "extra_position"
        private val DEFAULT_POSITION = -1
    }
}
