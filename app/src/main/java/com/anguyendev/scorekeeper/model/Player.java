package com.anguyendev.scorekeeper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amanda on 7/21/16.
 */
public class Player {
    private String mName;
    private List<Integer> mScores = new ArrayList<>();

    public Player(String name) {
        new Player(name, 0);
    }

    public Player(String name, int score){
        mName = name;
        mScores.add(score);
    }

    public int getLastScore(){
        if(!mScores.isEmpty()) {
            return mScores.get(mScores.size() - 1);
        } else {
            return 0;
        }
    }

    public void setName(String name){
        mName = name;
    }

    public String getName(){
        return mName;
    }

    public void addPoints(int numPointsToAdd) {
        mScores.add(getLastScore()+numPointsToAdd);
    }
}
