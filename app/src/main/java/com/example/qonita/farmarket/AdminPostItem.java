package com.example.qonita.farmarket;

/**
 * Created by Qonita on 10/14/2016.
 */

public class AdminPostItem {

    private String mPost, mJenisBarang, mNama, mUser;

    AdminPostItem (String text1, String text2, String text3, String text4){
        mPost = text1;
        mJenisBarang = text2;
        mNama = text3;
        mUser = text4;
    }

    public String getmPost(){
        return  mPost;
    }

    public void setmPost(String post){
        this.mPost = post;
    }

    public String getmJenisBarang(){
        return  mJenisBarang;
    }

    public void setmJenisBarang(String jenisBarang){
        this.mJenisBarang = jenisBarang;
    }

    public String getmNama() {return mNama; }

    public void setmNama(String nama){
        this.mNama = nama;
    }

    public String getmUser() {return mUser; }

    public void setmUser(String user){
        this.mUser = user;
    }
}
