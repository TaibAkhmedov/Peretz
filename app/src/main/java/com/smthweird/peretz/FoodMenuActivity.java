package com.smthweird.peretz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FoodMenuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView foodRecyclerView;
    private List<Food> foodList;
    private FoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu_activity);

        toolbar = findViewById(R.id.Toolbar_food_menu_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        initRecyclerView();


        App.getPeretzApi().getFoodsById(93, PeretzApi.PERETZ_KEY).enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {
                    foodList = response.body();
                    adapter = new FoodAdapter(FoodMenuActivity.this, foodList);
                    foodRecyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Проверьте подключение к сети", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }


    public void initRecyclerView() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        foodRecyclerView = findViewById(R.id.RecyclerView_foodMenuActivity);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerView.addItemDecoration(dividerItemDecoration);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, SearchFoodActivity.class);
            startActivity(intent);
        }
        return true;
    }


}







