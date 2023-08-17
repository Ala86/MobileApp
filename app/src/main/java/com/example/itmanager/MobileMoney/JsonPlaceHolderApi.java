
package com.example.itmanager.MobileMoney;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

         @POST("gswlive")
        @FormUrlEncoded
         Call<Post> createPost(@FieldMap Map<String, String> fields);

   // @FormUrlEncoded
  //  @POST("/login")
  //  Call<ResponseBody> createPost(@Field("email") String email, @Field("password") String password) ;

   // Call<ResponseBody> createPost(@Field("type") String type, @Field("meterNumber") String meterNumber, @Field("PhoneNumber") String PhoneNumber, @Field("amount") String amount);

    //Call<ResponseBody> createPost(Map<String, String> fields);


    //  @POST("gswlive")
      //  Call<Post> createPost(@Field("type") String type, @Field("meterNumber") String meterNumber, @Field("phoneNumber") String phoneNumber, @Field("amount") double amount);

      //  Call<Post> createPost(Map<String, String> fields);


        //  @POST("gswlive")
     // Call<List<Post>> createPost();
      //  @POST("/jayson")
       // FooResponse postJson(@Body FooRequest body);
        //@POST("gswlive")
        //Call<String> createPost(@Body String body);
}
