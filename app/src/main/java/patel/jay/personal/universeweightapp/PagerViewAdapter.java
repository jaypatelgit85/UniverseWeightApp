package patel.jay.personal.universeweightapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;


public abstract class PagerViewAdapter extends PagerAdapter {

    private PagerCard activeCard;
    private List<CardData> dataset;
    private LayoutInflater inflaterService;

    public PagerViewAdapter(Context applicationContext, List<CardData> dataset) {
        this.inflaterService = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataset = dataset;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Pager pager = (Pager) container;
        final PagerCard pagerCard = (PagerCard) inflaterService.inflate(patel.jay.personal.universeweightapp.R.layout.pager_card, null);
        final PagerView pagerContainer = (PagerView) pager.getParent();

        PagerCardContent pagerCardContent = pagerCard.getEcPagerCardContentList();
        PagerCardHead headView = pagerCardContent.getHeadView();

        headView.setHeight(pagerContainer.getCardHeight());

        Integer drawableRes = dataset.get(position).getHeadBackgroundResource();
        if (drawableRes != null) {
            headView.setHeadImageBitmap(BitmapFactory.decodeResource(pagerContainer.getResources(), drawableRes, new BitmapFactoryOptions()));
        }

        instantiateCard(inflaterService, headView, pagerCardContent, dataset.get(position));

        pager.addView(pagerCard, pagerContainer.getCardWidth(), pagerContainer.getCardHeight());
        return pagerCard;
    }


    public abstract void instantiateCard(LayoutInflater inflaterService, ViewGroup head, ListView list, CardData data);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        activeCard = (PagerCard) object;
    }

    public PagerCard getActiveCard() {
        return activeCard;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    public List<CardData> getDataset() {
        return dataset;
    }
}
