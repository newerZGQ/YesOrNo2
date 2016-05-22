package com.yesorno.zgq.smoothscroll;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 37902 on 2016/5/10.
 */
public class Book implements Parcelable {
    public int bookId;
    public String bookName;
    public Book(int bookId,String bookName){
        this.bookId  = bookId;
        this.bookName = bookName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>(){
        @Override
        public Book createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[0];
        }
    };
    public Book(Parcel in){
        bookId = in.readInt();
        bookName = in.readString();
    }
}
