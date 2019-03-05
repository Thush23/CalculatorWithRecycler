package com.example.calculator_recycler;
import android.os.Parcel;
import android.os.Parcelable;

public class Room implements Parcelable {
    private final String name;
    private final int chairCount;

    public String getName() {
        return name;
    }

    public int getChairCount() {
        return chairCount;
    }

    public Room(String name, int chairCount) {
        this.name = name;
        this.chairCount = chairCount;
    }

    protected Room(Parcel in) {
        name = in.readString();
        chairCount = in.readInt();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(chairCount);
    }
}
