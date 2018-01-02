package com.vj.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.vj.myapplication.R;
import com.vj.myapplication.adapters.SearchListAdapter;
import com.vj.myapplication.apis.ApiClient;
import com.vj.myapplication.models.Person;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by VJ on 12/30/2017.
 */

public class SearchListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    public static String TAG = SearchListFragment.class.getSimpleName();

    @BindView(R.id.search_list_fragment_swiperefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.search_list_fragment_listview)
    ListView recyclerView;

    List<Person> personList = new ArrayList<>();
    SearchListAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(this);

        searchAdapter = new SearchListAdapter(getActivity(), personList);
        recyclerView.setOnItemClickListener(this);
        recyclerView.setAdapter(searchAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        getData();
    }

    private void getData() {
        ApiClient.getRestService()
                .getPersons()
                .enqueue(apiResponse);
    }

    private Callback<JsonObject> apiResponse = new Callback<JsonObject>() {
        @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            Log.e(TAG, "=== apiResponse onResponse ===");
            Log.e(TAG, "Path: " + call.request().url().url().getPath());
            Log.e(TAG, "Method: " + call.request().method());
            Log.e(TAG, "Response: " + new Gson().toJson(response.body()));
            swipeRefreshLayout.setRefreshing(false);

            try {
                JsonObject root = response.body();
                if(root == null) return;

                int status = root.get("status").getAsInt();
                if(status != 1) return;
                JsonObject data = root.getAsJsonObject("data");
                if(data == null) return;

                personList = new Gson().fromJson(data.getAsJsonArray("persons"), new TypeToken<List<Person>>() { }.getType());

                searchAdapter.setPersonList(personList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.e(TAG, "=== apiResponse onFailure ===");
            Log.e(TAG, "Reason: " + t.getMessage());
            swipeRefreshLayout.setRefreshing(false);
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e(TAG, "=== onItemClick ===");

        Person person = (Person) adapterView.getAdapter().getItem(i);

        Bundle bundle = new Bundle();
        bundle.putSerializable("person", person);
        changeFragment(new DetailsFragment().addArguments(bundle), DetailsFragment.TAG);

    }
}
