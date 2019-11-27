package com.metro.metromobile.rest;
import com.metro.metromobile.model.BookingsResponse;
import com.metro.metromobile.dashboard.ui.cars.MyCars;
import com.metro.metromobile.model.RegisterRequestBody;
import com.metro.metromobile.model.request.AddBookingRequest;
import com.metro.metromobile.model.request.AddCarRequest;
import com.metro.metromobile.model.request.UpdatePasswordRequest;
import com.metro.metromobile.model.response.AuthenticationResponse;
import com.metro.metromobile.model.request.LoginRequestBody;
import com.metro.metromobile.model.response.BaseResponse;
import com.metro.metromobile.model.response.Brands;
import com.metro.metromobile.utils.manager.SessionManager;

import java.util.Base64;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface ServiceGenerator {
    @POST("user/login")
    Call<AuthenticationResponse> loginUser(@Body LoginRequestBody loginRequestBody);

    @POST("user/register")
    Call<AuthenticationResponse> registerUser(@Body RegisterRequestBody registerRequestBody);

    @GET("cars")
    Call<MyCars> getMyCars();

    @GET("brands")
    Call<Brands> getBrands();

    @POST("cars/add")
    Call<BaseResponse> addCar(@Body AddCarRequest addCarRequest);

    @GET("bookings")
    Call<BookingsResponse> getBookings();

    @POST("bookings/add")
    Call<BaseResponse> addBooking(@Body AddBookingRequest addBookingRequest);

    @POST("user/password")
    Call<BaseResponse> updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);

    @FormUrlEncoded
    @POST("user/profile")
    Call<BaseResponse> updateUserProfile(@Field("first_name") String firstName,
                                                            @Field("last_name") String lastName,
                                                            @Field("about") String about,
                                                            @Field("avatar") String avatar
                                         );



}

