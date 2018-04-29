package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {


        //find place of origin view id, get the origin from sandwich object  and set the text
        TextView originTV = findViewById(R.id.origin_tv);
        String placeofOrigin = sandwich.getPlaceOfOrigin();
        if (placeofOrigin == null || placeofOrigin.equals("")) {
            placeofOrigin = getString(R.string.origin_error_message); }
        originTV.setText(placeofOrigin);

        //find description view id, get the description from sandwich object and set the text
        TextView descriptionTV = findViewById(R.id.description_tv);
        String description = sandwich.getDescription();
        descriptionTV.setText(description);

        //find ingredients view id, get the ingredients list from sandwich object and set the text
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);
        String ingredients;
        List<String> ingredientsList = sandwich.getIngredients();
            if (ingredientsList.isEmpty()) {
                ingredients = getString(R.string.ingredients_error_message); }
             else {
            StringBuilder builder = new StringBuilder();
                for (int i = 0; i < ingredientsList.size() ; i++) {
                    builder.append(ingredientsList.get(i));
                    // add line break until last item is received from the list
                    if (i != ingredientsList.size() - 1)
                        builder.append('\n'); }
                ingredients = builder.toString(); }
        ingredientsTV.setText(ingredients);

        //find also known as view id, get the also known as list from sandwich object and set the text
        TextView alsoknownasTV = findViewById(R.id.also_known_tv);
        String alsoKnownAs;
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();
             if (alsoKnownAsList.isEmpty()){
            alsoKnownAs = getString(R.string.alsoknownas_error_message);
             }
             else {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < alsoKnownAsList.size() ; i++) {
                builder.append(alsoKnownAsList.get(i));
                // add line break until last item is received from the list
                if (i != alsoKnownAsList.size() - 1)
                    builder.append('\n'); }
            alsoKnownAs = builder.toString(); }
        alsoknownasTV.setText(alsoKnownAs);

    }
}
