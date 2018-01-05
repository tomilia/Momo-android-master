package com.example.tommylee.myapplication;
        import android.os.Parcel;
        import android.os.Parcelable;

public class Artists implements Parcelable {

    private String name;
    private boolean isFavorite;

    public Artists(String name, boolean isFavorite) {
        this.name = name;
        this.isFavorite = isFavorite;
    }

    protected Artists(Parcel in) {
        name = in.readString();
    }

    public String getName() {
        return name;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artists)) return false;

        Artists Artists = (Artists) o;

        if (isFavorite() != Artists.isFavorite()) return false;
        return getName() != null ? getName().equals(Artists.getName()) : Artists.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isFavorite() ? 1 : 0);
        return result;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Artists> CREATOR = new Creator<Artists>() {
        @Override
        public Artists createFromParcel(Parcel in) {
            return new Artists(in);
        }

        @Override
        public Artists[] newArray(int size) {
            return new Artists[size];
        }
    };
}