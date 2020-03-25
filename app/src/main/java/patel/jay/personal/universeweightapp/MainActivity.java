package patel.jay.personal.universeweightapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;


@SuppressLint("SetTextI18n")
public class MainActivity extends Activity {

    private PagerView ecPagerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Create adapter for pager
        PagerViewAdapter adapter = new PagerViewAdapter(this, new Dataset().getDataset()) {


            @Override
            public void instantiateCard(LayoutInflater inflaterService, final ViewGroup head, final ListView list, final CardData data) {
                final CardData cardData = (CardData) data;

                // Create adapter for list inside a card and set adapter to card content
                ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), cardData.getListItems());
                list.setAdapter(arrayAdapter);
                list.setDivider(getResources().getDrawable(R.drawable.list_divider));
                list.setDividerHeight((int) pxFromDp(getApplicationContext(), 0.5f));
                list.setSelector(R.color.transparent);
                list.setBackgroundColor(Color.WHITE);
                list.setCacheColorHint(Color.TRANSPARENT);

                // Add gradient to root header view
                View gradient = new View(getApplicationContext());
                gradient.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.MATCH_PARENT));
                //gradient.setBackgroundDrawable(getResources().getDrawable(R.drawable.card_head_gradient));
                head.addView(gradient);

                // Inflate main header layout and attach it to header root view
                inflaterService.inflate(R.layout.simple_head, head);

                // Set header data from data object
                TextView title = (TextView) head.findViewById(R.id.title);
                title.setText(cardData.getHeadTitle());

                head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {

                        String clickedPlanet = cardData.getHeadTitle();
                        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                        Intent intent = new Intent(MainActivity.this.getBaseContext(), WeightCalculator.class);
                        intent.putExtra("planetName", clickedPlanet);
                        startActivity(intent, activityOptions.toBundle());
                    }
                });
            }
        };

        ecPagerView = (PagerView) findViewById(R.id.ec_pager_element);

        ecPagerView.setPagerViewAdapter(adapter);
        ecPagerView.setBackgroundSwitcherView((BackgroundImageSwitcher) findViewById(R.id.ec_bg_switcher_element));

        final ItemsCountView itemsCountView = (ItemsCountView) findViewById(R.id.items_count_view);
        ecPagerView.setOnCardSelectedListener(new PagerView.OnCardSelectedListener() {
            @Override
            public void cardSelected(int newPosition, int oldPosition, int totalElements) {
                itemsCountView.update(newPosition, oldPosition, totalElements);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!ecPagerView.collapse())
            super.onBackPressed();
    }

    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

}