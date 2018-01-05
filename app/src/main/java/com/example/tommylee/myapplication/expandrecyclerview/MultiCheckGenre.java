package com.example.tommylee.myapplication.expandrecyclerview;

/**
 * Created by tommylee on 31/12/2017.
 */
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;
import com.example.tommylee.myapplication.Artists;
import java.util.List;

public class MultiCheckGenre extends MultiCheckExpandableGroup {

    private int iconResId;

    public MultiCheckGenre(String title, List<Artists> items, int iconResId) {
        super(title, items);
        this.iconResId = iconResId;
    }

    public int getIconResId() {
        return iconResId;
    }
}