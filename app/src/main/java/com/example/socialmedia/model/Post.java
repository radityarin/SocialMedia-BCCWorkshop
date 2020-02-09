package com.example.socialmedia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
//    Post
//- id: Int
//- id_user: Int
//- content: String
//- isDeleted: Boolean
//- createdAt: Timestamp
//- updatedAt: Timestamp

    private int id, id_user;
    private String content, createdAt, updatedAt;
    private boolean isDeleted;

    public Post(int id, int id_user, String content, String createdAt, String updatedAt, boolean isDeleted) {
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

    public int getId_user() {
        return id_user;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.id_user);
        dest.writeString(this.content);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeByte(this.isDeleted ? (byte) 1 : (byte) 0);
    }

    protected Post(Parcel in) {
        this.id = in.readInt();
        this.id_user = in.readInt();
        this.content = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.isDeleted = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
