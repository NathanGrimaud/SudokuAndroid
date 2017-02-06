package com.example.nathan.sudoku;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nathan on 2/2/17.
 */

public class vGrid  implements Parcelable {
    private Integer lvl;
    private Integer num;
    private Integer done;
    private String grid;

    public vGrid(Integer lvl, Integer num, Integer done) {
        this.lvl = lvl;
        this.num = num;
        this.done = done;
    }

    public vGrid(Parcel in){
        String[] data = new String[4];
        in.readStringArray(data);
        this.done = Integer.valueOf(data[0]);
        this.lvl = Integer.valueOf(data[1]);
        this.num = Integer.valueOf(data[2]);
        this.grid = data[3];
    }


    public static final Creator<vGrid> CREATOR = new Creator<vGrid>() {
        @Override
        public vGrid createFromParcel(Parcel in) {
            return new vGrid(in);
        }

        @Override
        public vGrid[] newArray(int size) {
            return new vGrid[size];
        }
    };

    public Integer getLvl() {
        return lvl;
    }

    public void setLvl(Integer lvl) {
        this.lvl = lvl;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(
                new String[]{
                        this.done.toString(),
                        this.lvl.toString(),
                        this.num.toString(),
                        this.grid
                }
        );
    }
}
