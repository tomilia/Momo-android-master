package com.example.tommylee.myapplication.expandrecyclerview;

/**
 * Created by tommylee on 31/12/2017.
 */

import com.example.tommylee.myapplication.Artists;
import com.example.tommylee.myapplication.R;
import com.example.tommylee.myapplication.expandrecyclerview.MultiCheckGenre;

import java.util.Arrays;
import java.util.List;

public class GenreDataFactory {

    public static List<Genre> makeGenres() {
        return Arrays.asList(makeRockGenre());
    }

    public static List<MultiCheckGenre> makeMultiCheckGenres() {
        return Arrays.asList(makeMultiCheckRockGenre(),makeMultiCheckGenre());
    }


    public static Genre makeRockGenre() {
        return new Genre("深圳", makeRockArtists(), R.drawable.back);
    }

    public static MultiCheckGenre makeMultiCheckRockGenre() {
        return new MultiCheckGenre("深圳", makeRockArtists(), R.drawable.back);
    }
    public static MultiCheckGenre makeMultiCheckGenre() {
        return new MultiCheckGenre("香港", makeRockArtists(), R.drawable.back);
    }



    public static List<Artists> makeRockArtists() {
        Artists queen = new Artists("AAA區", true);
        Artists styx = new Artists("BBB區", false);
        Artists reoSpeedwagon = new Artists("CCC區", false);
        Artists boston = new Artists("DDD區", true);

        return Arrays.asList(queen, styx, reoSpeedwagon, boston);
    }


}