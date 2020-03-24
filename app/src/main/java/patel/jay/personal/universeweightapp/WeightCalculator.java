package patel.jay.personal.universeweightapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Window;
import java.util.Random;

public class WeightCalculator extends AppCompatActivity {
    ConstraintLayout mainLayout;
    String planet;
    double planetWeightFactor = 1.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_weight_calculator);
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
        mainLayout = findViewById(R.id.mainConstraintLayout);

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


}
