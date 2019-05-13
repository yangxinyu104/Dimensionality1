package com.bw.movie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.SearchBeonAdapter;
import com.bw.movie.adapter.SearchHotAdapter;
import com.bw.movie.adapter.SearchShowingAdapter;
import com.bw.movie.app.MyApplication;
import com.bw.movie.bean.AttentionBean;
import com.bw.movie.contract.ContractInterFace;
import com.bw.movie.presenter.MyPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements ContractInterFace.ISearchFilm {
    @BindView(R.id.search_hot)
    RadioButton searchHot;
    @BindView(R.id.search_showing)
    RadioButton searchShowing;
    @BindView(R.id.search_beon)
    RadioButton searchBeon;
    @BindView(R.id.search_RadioGroup)
    RadioGroup searchRadioGroup;
    @BindView(R.id.search_RecyclerView)
    RecyclerView searchRecyclerView;
    @BindView(R.id.search_finish)
    ImageView searchFinish;
    public ContractInterFace.IPresenter iPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        HotList();
        iPresenter = new MyPresenter<>(this);
    }
    private void HotList() {
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchHotAdapter adapter = new SearchHotAdapter(MyApplication.hotList,this);
        searchRecyclerView.setAdapter(adapter);
        adapter.setOnItemClick(new SearchHotAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent=  new Intent(SearchActivity.this,DetailsActivity.class);
                MyApplication.flag = MyApplication.BeonList.get(position).flag;
                intent.putExtra("aid",MyApplication.hotList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.search_hot, R.id.search_showing, R.id.search_beon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_hot:
                HotList();
                break;
            case R.id.search_showing:
                SearchShowingAdapter adapter = new SearchShowingAdapter(MyApplication.ShowingList,this);
                searchRecyclerView.setAdapter(adapter);
                adapter.setOnItemClick(new SearchShowingAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=  new Intent(SearchActivity.this,DetailsActivity.class);
                        MyApplication.flag = MyApplication.BeonList.get(position).flag;
                        intent.putExtra("aid",MyApplication.ShowingList.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
            case R.id.search_beon:
                SearchBeonAdapter adapters = new SearchBeonAdapter(MyApplication.BeonList,this);
                searchRecyclerView.setAdapter(adapters);
                adapters.setOnItemClick(new SearchBeonAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent=  new Intent(SearchActivity.this,DetailsActivity.class);
                        MyApplication.flag = MyApplication.BeonList.get(position).flag;
                        intent.putExtra("aid",MyApplication.BeonList.get(position).getId());
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    @OnClick(R.id.search_finish)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void attention(AttentionBean attentionBean) {
        Toast.makeText(this, attentionBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noattention(AttentionBean attentionBean) {
        Toast.makeText(this, attentionBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
