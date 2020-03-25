package patel.jay.personal.universeweightapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public abstract class CardContents<T> extends ArrayAdapter<T> {
    private boolean zeroItemsMode = true;

    public CardContents(@NonNull Context context, @LayoutRes int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public final int getCount() {
        return zeroItemsMode ? 0 : super.getCount();
    }

    @NonNull
    @Override
    public abstract View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent);

    protected final void enableZeroItemsMode() {
        this.zeroItemsMode = true;
        notifyDataSetChanged();
    }

    protected final void disableZeroItemsMode() {
        this.zeroItemsMode = false;
        notifyDataSetChanged();
    }
}
