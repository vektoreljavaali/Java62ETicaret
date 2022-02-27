package com.vektorel.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.util.JsonMapper;
import com.google.gson.Gson;
import com.vektorel.R;
import com.vektorel.adapter.UserAdapter;
import com.vektorel.databinding.FragmentHomeBinding;
import com.vektorel.model.News;
import com.vektorel.model.UserProfile;
import com.vektorel.utility.StaticValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<News> newsList ;
    private List<UserProfile> profiles;
    private RecyclerView recyclerView;
    //NewsApiClient newsApiClient = new NewsApiClient("YOUR_API_KEY");
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        newsList = new ArrayList<>();
        profiles = new ArrayList<>();
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.rcwUserProfileList);
        getNews();
        return root;
    }

    private void getNews(){
        /**
         * https://newsapi.org/v2/everything?q=ankara&language=tr&from=2022-02-26&to=2022-02-27&sortBy=popularity&apiKey=86fec14ae3a54bd8a8cbb0f9f3ab517a
         *
         * everything
         */
        String url = "https://randomuser.me/api/?results=20";
        RequestQueue queue = Volley.newRequestQueue(this.getContext());


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
               url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /**
                         * Yapılan istek başarılı ise burada işlem yaparsın
                         */
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("results");
                            for(int i=0;i< jsonArray.length();i++){
                                JSONObject jo = jsonArray.getJSONObject(i);
                                Gson gson = new Gson();
                                UserProfile userProfile = gson.fromJson(jo.toString(),UserProfile.class);
                                profiles.add(userProfile);

                            }
                            Listele();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * Hata almış ise burada işlem yaparsın.
                 */
                Log.e("TAG", "onErrorResponse: "+error.toString());

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void Listele() {
        UserAdapter userAdapter = new UserAdapter(this.getContext(),profiles);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(userAdapter);
    }


    private void getAllNews(){

        String url = "https://randomuser.me/api/?results=20";

       JsonArrayRequest jsonArrayRequest=  new JsonArrayRequest(url,new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Hata...: "+error.toString());
            }
        }

        );
        RequestQueue queue = Volley.newRequestQueue(this.getContext());

        queue.add(jsonArrayRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}