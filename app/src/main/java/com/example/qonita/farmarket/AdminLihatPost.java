package com.example.qonita.farmarket;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdminLihatPost extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mAdminLihatPost;
    AdminPostAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "KelolaDataBencana";
    private List<AdminPostItem> adminPostList = new ArrayList<>();
    String ResponseStatus;
    private static final String TAG_SUCCESS = "success";
    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_lihat_post);
        pdialog = new ProgressDialog(AdminLihatPost.this);
        pdialog.setMessage("Proses...");
        pdialog.setCanceledOnTouchOutside(false);
        pdialog.show();
        mAdminLihatPost = (RecyclerView) findViewById(R.id.rv_admin_lihat_post);
        mAdminLihatPost.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdminLihatPost.setLayoutManager(mLayoutManager);
//        mAdapter = new AdminPostAdapter(this));
        mAdminLihatPost.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);
        mAdminLihatPost.addItemDecoration(itemDecoration);
        mAdminLihatPost.addOnItemTouchListener(new RecyclerTouchListener(this, mAdminLihatPost, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                AdminPostItem myActivity = adminPostList.get(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    public boolean onContextItemSelected(MenuItem item) {
        /*mAdapter.getItemSelected(item);
        int position;
        position =mAdapter.getPosition();
        switch (item.getItemId()){
            case R.id.edit:
                return true;
            case R.id.delete:
                return true;
        }*/
        return super.onContextItemSelected(item);

    }

    protected void onResume(){
        super.onResume();
    }


    @Override
    public void onClick(View v) {

    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}
