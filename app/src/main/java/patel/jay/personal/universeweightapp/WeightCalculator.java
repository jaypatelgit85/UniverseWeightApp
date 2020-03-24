package patel.jay.personal.universeweightapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.util.Random;

public class WeightCalculator extends AppCompatActivity {
    ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_weight_calculator);

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

    }


}
