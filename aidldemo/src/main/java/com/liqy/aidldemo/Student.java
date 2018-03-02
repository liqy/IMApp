package com.liqy.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liqy on 2018/3/2.
 */

public class Student implements Parcelable {
    public String id;
    public String nickName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nickName);
    }



    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public Student(String id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    protected Student(Parcel in) {
        readFromParcel(in);
    }

    /**
     * 也就是AIDL传值的时候并声明 in inout
     * @param in
     */
    public void readFromParcel(Parcel in){
        this.id = in.readString();
        this.nickName = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
