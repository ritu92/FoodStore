package com.example.malik.foodstore;

import com.example.malik.foodstore.model.FoodItemList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.android.volley.Request.Method.GET;

/**
 * Created by malik on 7/6/2017.
 */

public interface ApiService {
    /*
    * Retrofit get annotation with our URL
    * And our method that will return us food items requested
   */
    // url = http://rjtmobile.com/ansari/fos/fosapp/fos_food_loc.php?city=delhi
    @GET("fos/fosapp/fos_food_loc.php")
    //@GET( "/city=delhi")
    Call<FoodItemList> getMyJSON(@Query("city") String city);
}
