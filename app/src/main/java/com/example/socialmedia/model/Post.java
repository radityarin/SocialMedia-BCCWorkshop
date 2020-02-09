package com.example.socialmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{
    private int id, id_user;
    private String content, createdAt, updatedAt;
    private char isDeleted;

    public Post(int id, int id_user, String content, String createdAt, String updatedAt, char isDeleted) {
        this.id = id;
        this.id_user = id_user;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public char getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(char isDeleted) {
        this.isDeleted = isDeleted;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    protected Post(Parcel in) {
        id = in.readInt();
        id_user = in.readInt();
        content = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        isDeleted = (char) in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_user);
        dest.writeString(content);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeInt((int) isDeleted);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}