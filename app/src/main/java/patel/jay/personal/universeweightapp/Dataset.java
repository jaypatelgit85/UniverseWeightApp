package patel.jay.personal.universeweightapp;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dataset {

    private List<CardData> dataset;

    public Dataset() {
        dataset = new ArrayList<>(8);

        CardData mercury = new CardData();
        mercury.setMainBackgroundResource(R.drawable.mercury);
        mercury.setHeadBackgroundResource(R.drawable.mercury);
        mercury.setHeadTitle("Mercury");
        dataset.add(mercury);

        CardData venus = new CardData();
        venus.setMainBackgroundResource(R.drawable.venus);
        venus.setHeadBackgroundResource(R.drawable.venus);
        venus.setHeadTitle("Venus");
        dataset.add(venus);

        CardData earth = new CardData();
        earth.setMainBackgroundResource(R.drawable.earth);
        earth.setHeadBackgroundResource(R.drawable.earth);
        earth.setHeadTitle("Home");
        dataset.add(earth);

        CardData mars = new CardData();
        mars.setMainBackgroundResource(R.drawable.mars);
        mars.setHeadBackgroundResource(R.drawable.mars);
        mars.setHeadTitle("Mars");
        dataset.add(mars);

        CardData jupiter = new CardData();
        jupiter.setMainBackgroundResource(R.drawable.jupiter);
        jupiter.setHeadBackgroundResource(R.drawable.jupiter);
        jupiter.setHeadTitle("Jupiter");
        dataset.add(jupiter);

        CardData saturn = new CardData();
        saturn.setMainBackgroundResource(R.drawable.saturn);
        saturn.setHeadBackgroundResource(R.drawable.saturn);
        saturn.setHeadTitle("Saturn");
        dataset.add(saturn);

        CardData neptune = new CardData();
        neptune.setMainBackgroundResource(R.drawable.neptune);
        neptune.setHeadBackgroundResource(R.drawable.neptune);
        neptune.setHeadTitle("Neptune");
        dataset.add(neptune);

        CardData uranus = new CardData();
        uranus.setMainBackgroundResource(R.drawable.uranus);
        uranus.setHeadBackgroundResource(R.drawable.uranus);
        uranus.setHeadTitle("Uranus");
        dataset.add(uranus);

    }

    public List<CardData> getDataset() {
        Collections.addAll(dataset);
        return dataset;
    }


}

