package patel.jay.personal.universeweightapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


@SuppressLint({"SetTextI18n", "InflateParams"})
public class ArrayAdapter extends CardContents {

    public ArrayAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, R.layout.list_element, objects);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        return inflater.inflate(R.layout.list_element, null);
    }


}