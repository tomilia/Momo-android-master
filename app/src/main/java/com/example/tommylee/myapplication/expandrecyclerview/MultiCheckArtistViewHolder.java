package com.example.tommylee.myapplication.expandrecyclerview;

/**
 * Created by tommylee on 31/12/2017.
 */
import android.util.Log;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.example.tommylee.myapplication.R;

public class MultiCheckArtistViewHolder extends CheckableChildViewHolder {

    private CheckedTextView childCheckedTextView;

    public MultiCheckArtistViewHolder(View itemView) {
        super(itemView);
        childCheckedTextView =
                (CheckedTextView) itemView.findViewById(R.id.list_item_multicheck_artist_name);

    }

    @Override
    public Checkable getCheckable() {
        return childCheckedTextView;
    }

    public void setArtistName(String artistName) {
        childCheckedTextView.setText(artistName);
    }
}
