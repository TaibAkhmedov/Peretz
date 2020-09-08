package com.smthweird.peretz;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    List<Food> foodList;
    Context context;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        holder.bind(foodList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return foodList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


    public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView foodImage;
        private TextView foodTitle;
        private TextView foodDescription;
        private TextView foodPrice;
        private TextView foodCount;
        private Button addFoodBtn;
        private Button deleteFoodBtn;
        private int count;

        private Food mFood;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.ImageView_foodImage);
            foodTitle = itemView.findViewById(R.id.TextView_foodTitle);
            foodDescription = itemView.findViewById(R.id.TextView_foodDescription);
            foodPrice = itemView.findViewById(R.id.TextView_foodPrice);
            foodCount = itemView.findViewById(R.id.TextView_foodCount);
            addFoodBtn = itemView.findViewById(R.id.Button_addFood);
            deleteFoodBtn = itemView.findViewById(R.id.Button_deleteFood);

            addFoodBtn.setOnClickListener(this);
            deleteFoodBtn.setOnClickListener(this);
        }

        public void bind(Food food) {
            mFood = food;
            foodTitle.setText(food.getName());
            foodDescription.setText(food.getDescription());
            foodPrice.setText(food.getPrice() + " ₽");

            count = 0;
            count = App.loadPref(food.getId());
            foodCount.setText(String.valueOf(count));

            if (count <= 0) {
                deleteFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
            } else {
                deleteFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
            }

            Glide.with(foodImage)
                    .load(food.getImageApp())
                    .into(foodImage);
        }

        @Override
        public void onClick(View itemView) {
            switch (itemView.getId()) {
                case R.id.Button_deleteFood:
                    deleteCounter();
                    break;
                case R.id.Button_addFood:
                    addCounter();
                    break;
            }
        }

        public void addCounter() {
            count++;
            foodCount.setText(count + "");

            App.savePref(mFood.getId(), count);

            if (count <= 0) {
                deleteFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
            } else {
                deleteFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
            }
        }

        public void deleteCounter() {
            count--;
            foodCount.setText(count + "");

            App.deletePref(mFood.getId());
            App.savePref(mFood.getId(), count);

            if (count <= 0) {
                deleteFoodBtn.setVisibility(View.INVISIBLE);
                foodCount.setVisibility(View.INVISIBLE);
            } else {
                deleteFoodBtn.setVisibility(View.VISIBLE);
                foodCount.setVisibility(View.VISIBLE);
            }
        }
    }
}
