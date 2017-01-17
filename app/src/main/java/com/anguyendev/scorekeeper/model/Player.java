package com.anguyendev.scorekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amanda on 7/21/16.
 */
public class Player implements Parcelable {
    private String mName;
    private int mScore;
//    private List<Integer> mScores = new ArrayList<>();

    public Player(String name) {
        this(name, 0);
    }

    public Player(String name, int score){
        mName = name;
        mScore = score;
//        mScores.add(score);
    }

    private Player(Parcel in) {
        mName = in.readString();
        mScore = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

//    public int getLastScore(){
//        if(!mScores.isEmpty()) {
//            return mScores.get(mScores.size() - 1);
//        } else {
//            return 0;
//        }
//    }

    public int getScore(){
        return mScore;
    }

    public void setName(String name){
        mName = name;
    }

    public String getName(){
        return mName;
    }

    public void setScore(int score) {
//        mScores.add(score);
        mScore = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeInt(mScore);
    }
}
