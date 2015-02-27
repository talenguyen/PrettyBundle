package com.tale.prettybundle.sample.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giang on 2/24/15.
 */
public class Person implements Parcelable {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.age);
    }

    public Person() {
    }

    private Person(Parcel in) {
        this.name = in.readString();
        this.age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ",age=" + age +
                '}';
    }
}
