package patel.jay.personal.universeweightapp;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * Card content list it's main element of card content - just a list view with custom header and animations.
 */
public class PagerCardContent extends ListView {

    private boolean scrollDisabled;
    private int mPosition;

    private CardContentListAdapter contentListItemAdapter;

    private PagerCardHead headView;

    public PagerCardContent(Context context) {
        super(context);
        init(context);
    }

    public PagerCardContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PagerCardContent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        headView = new PagerCardHead(context);
        headView.setBackgroundColor(Color.RED);
        headView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));
        addHeaderView(headView);
//        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    public CardContentListAdapter getContentListItemAdapter() {
        return contentListItemAdapter;
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof CardContentListAdapter) {
            this.contentListItemAdapter = (CardContentListAdapter) adapter;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int actionMasked = ev.getActionMasked() & MotionEvent.ACTION_MASK;
        // Ignore move events if scroll disabled
        if (scrollDisabled && actionMasked == MotionEvent.ACTION_MOVE) {
            return true;
        }
        // Ignore scroll events if scroll disabled
        if (scrollDisabled && actionMasked == MotionEvent.ACTION_SCROLL) {
            return true;
        }
        // Save the event initial position
        if (actionMasked == MotionEvent.ACTION_DOWN) {
            mPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
            return super.dispatchTouchEvent(ev);
        }
        // Check if we are still in the same position, otherwise cancel event
        int eventPosition = pointToPosition((int) ev.getX(), (int) ev.getY());
        if (actionMasked == MotionEvent.ACTION_UP) {
            if (eventPosition != mPosition) {
                ev.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.dispatchTouchEvent(ev);
    }


    protected PagerCardHead getHeadView() {
        return headView;
    }

    protected void animateWidth(int targetWidth, int duration, int delay) {
        // reset own width for smooth animation and avoid values like 'MATCH_PARENT'
        this.getLayoutParams().width = this.getWidth();

        ValueAnimator widthAnimation = new ValueAnimator();
        widthAnimation.setInterpolator(new DecelerateInterpolator());
        widthAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams pagerLayoutParams = getLayoutParams();
                pagerLayoutParams.width = (int) animation.getAnimatedValue();
                setLayoutParams(pagerLayoutParams);
            }
        });
        widthAnimation.setIntValues(getWidth(), targetWidth);
        widthAnimation.setStartDelay(delay);
        widthAnimation.setDuration(duration);
        widthAnimation.start();
    }

    protected final void hideListElements() {
        getContentListItemAdapter().enableZeroItemsMode();
    }

    protected final void showListElements() {
        getContentListItemAdapter().disableZeroItemsMode();
    }

}
