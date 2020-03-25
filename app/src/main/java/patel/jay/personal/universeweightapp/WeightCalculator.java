package patel.jay.personal.universeweightapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Random;

public class WeightCalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ConstraintLayout mainLayout;
    String planet;
    int pos = 0;
    double planetWeightFactor = 1.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_weight_calculator);

        Spinner myspinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter instance
        // This attaches your string list to the ArrayAdapter
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.list, android.R.layout.simple_spinner_item);

        // Set the default Dropdown layout for the Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Attach the Adapter to the Spinner
        myspinner.setAdapter(adapter);

        // Select the default first selection - this usually prevents a select event on startup
        myspinner.setSelection(0, false);

        // Attach our Activity class as the 'listener' for this object's events
        myspinner.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        planet = intent.getStringExtra("planetName");
        Random r = new Random();
        int randomAnimatorPicker = r.nextInt(3) + 1;

        Log.d("random", randomAnimatorPicker + "");

        switch (randomAnimatorPicker) {
            case 1:
                Explode explodeAnimation = new Explode();
                explodeAnimation.setDuration(500);
                getWindow().setEnterTransition(explodeAnimation);
                break;

            case 2:
                Fade fadeAnimation = new Fade();
                fadeAnimation.setDuration(500);
                getWindow().setEnterTransition(fadeAnimation);
                break;

            case 3:
                Slide slideAnimation = new Slide();
                slideAnimation.setDuration(500);
                getWindow().setEnterTransition(slideAnimation);
                break;

            default:
                Log.d("", "");

        }
        mainLayout = findViewById(R.id.mainConstaintLayout);

        backgroundSwitcher(planet);
    }

    private void backgroundSwitcher(String Planet) {

        switch (planet) {

            case "Mercury":
                mainLayout.setBackgroundResource(R.drawable.mercury_main);
                planetWeightFactor = 0.3772;
                break;

            case "Venus":
                mainLayout.setBackgroundResource(R.drawable.venus_main);
                planetWeightFactor = 0.9042;
                break;

            case "Home":
                mainLayout.setBackgroundResource(R.drawable.earth_main);
                break;

            case "Mars":
                mainLayout.setBackgroundResource(R.drawable.mars_main);
                planetWeightFactor = 0.3783;
                break;

            case "Jupiter":
                mainLayout.setBackgroundResource(R.drawable.jupiter_main);
                planetWeightFactor = 2.5270;
                break;

            case "Saturn":
                mainLayout.setBackgroundResource(R.drawable.saturn_main);
                planetWeightFactor = 1.0642;
                break;

            case "Neptune":
                mainLayout.setBackgroundResource(R.drawable.neptune_main);
                planetWeightFactor = 1.1366;
                break;

            case "Uranus":
                mainLayout.setBackgroundResource(R.drawable.uranus_image);
                planetWeightFactor = 0.8858;
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if (parent.getId() == R.id.spinner) {

            // Get the string that was selected using this...
            pos = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @SuppressLint("DefaultLocale")
    public void calculateWeight(View view) {
        Button button = findViewById(R.id.calculate);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(button.getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        String what;
        if (pos == 0) {
            what = "Pounds (Lb)";
        } else {
            what = "Kilograms (Kg)";
        }
        EditText userInput = findViewById(R.id.userInput);

        if (userInput.getText().toString().isEmpty() || userInput.getText().toString().equals(".")) {
            userInput.setError("Invalid Input, Please Try Again");
        } else {
            double actualWeight = Double.valueOf(userInput.getText().toString());
            double calculatedWeight = actualWeight * planetWeightFactor;

            TypeWriter planetNameWeight = findViewById(R.id.planetWeightName);
            planetNameWeight.setCharacterDelay(100);
            planetNameWeight.animateText("Your weight on " + planet + " will be : " + String.format("%.2f", calculatedWeight) + " " + what);
        }


    }
}
