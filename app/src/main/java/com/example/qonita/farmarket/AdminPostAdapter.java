package com.example.qonita.farmarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Qonita on 10/14/2016.
 */

public class AdminPostAdapter extends RecyclerView.Adapter<AdminPostAdapter.AdminPostHolder> {

    private static String LOG_TAG="AdminPostAdapter";
    private ArrayList<AdminPostItem> adminPostList;
    private static ClickListener clickListener;
    private Context c;
    private String nama_post;

    private int position;

    public AdminPostAdapter(ArrayList<AdminPostItem> dataAdminPost) {
        adminPostList = dataAdminPost;
            }

    public int getPosition(){
            return position;
            }

    public void setPosition(int position){
            this.position = position;
            }


    public AdminPostAdapter(Context c, ArrayList<AdminPostItem>adminPostList){
            this.c = c;
            this.adminPostList = adminPostList;
        }

    public static class AdminPostHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnCreateContextMenuListener {

        TextView mPost, mJenisBarang, mNama, mUser;

        private ClickListener longclick;

        public AdminPostHolder(View itemView) {
            super(itemView);
            //idAdminPost = (TextView) itemView.findViewById(R.id.idAdminPost);
            mPost = (TextView) itemView.findViewById(R.id.tv_nama_post);
            mJenisBarang = (TextView) itemView.findViewById(R.id.tv_jenis_barang);
            mNama = (TextView) itemView.findViewById(R.id.tv_nama_barang);
            mUser = (TextView) itemView.findViewById(R.id.tv_oleh_user);

            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.add(0, v.getId(), 0, "Validasi Post");//groupId, itemId, order, title
    }

    public void setLongClickListener(ClickListener lc) {
        this.longclick = lc;
    }

    public boolean onLongClick(View v) {
        this.longclick.onItemLongClick(getPosition(), v);
        return false;
    }

}

    public void setOnItemLongClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public AdminPostHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_admin_lihat_post, parent, false);
        AdminPostHolder AdminPostHolder = new AdminPostHolder(view);
        return AdminPostHolder;


    }

    @Override
    public void onBindViewHolder(final AdminPostHolder holder, int position) {
        holder.mPost.setText(adminPostList.get(position).getmPost());
        holder.mJenisBarang.setText("Jenis Barang : " + adminPostList.get(position).getmJenisBarang());
        holder.mNama.setText("Nama : " + adminPostList.get(position).getmNama());
        holder.mUser.setText("Oleh : "+ adminPostList.get(position).getmUser());
        holder.setLongClickListener(new ClickListener() {
            @Override
            public void onItemLongClick(int pos, View v) {
                setPosition(holder.getPosition());
                nama_post = adminPostList.get(pos).getmNama();
            }
        });
    }

    @Override
    public int getItemCount() {
        return adminPostList.size();
    }

    public interface ClickListener {
        public void onItemLongClick(int position, View v);

    }

    public void getItemSelected(MenuItem item) {
//        Toast.makeText(c, item.getTitle() + " : "+nama_AdminPost, Toast.LENGTH_LONG).show();
        if (item.getTitle().equals("Validasi Post")) {
            Intent edit_go = new Intent(c, AdminValidasi.class);
            edit_go.putExtra("nama_post", nama_post);
            edit_go.putExtra("jenis_barang", adminPostList.get(position).getmJenisBarang());
            edit_go.putExtra("nama", adminPostList.get(position).getmNama());
            edit_go.putExtra("oleh_user", adminPostList.get(position).getmUser());

            c.startActivity(edit_go);
        } else {

        }
    }
}
