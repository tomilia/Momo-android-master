package com.example.tommylee.myapplication.expandrecyclerview;

/**
 * Created by tommylee on 31/12/2017.
 */


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnChildCheckChangedListener;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.example.tommylee.myapplication.Artists;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.expandrecyclerview.GenreViewHolder;
import java.util.List;

public class MultiCheckGenreAdapter extends
        CheckableChildRecyclerViewAdapter<GenreViewHolder, MultiCheckArtistViewHolder> {

    public MultiCheckGenreAdapter(List<MultiCheckGenre> groups) {
        super(groups);
    }

    @Override
    public MultiCheckArtistViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.framerecyclermulti, parent, false);
        return new MultiCheckArtistViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(MultiCheckArtistViewHolder holder, int position,
                                           CheckedExpandableGroup group, int childIndex) {
        final Artists artist = (Artists) group.getItems().get(childIndex);
        holder.setArtistName(artist.getName());
        holder.setOnChildCheckedListener(new OnChildCheckChangedListener() {
            @Override
            public void onChildCheckChanged(View view,boolean b,int c) {
                


            }});
    }

    @Override
    public GenreViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selectionlocatfrag, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(GenreViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        Log.d("MEME", group.getTitle());
        holder.setGenreTitle(group);
    }
}