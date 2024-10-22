package com.exercise.lab11.api;

import com.exercise.lab11.model.Trainee;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TraineeService {
    String TRAINEES = "NhanVien";

    @GET(TRAINEES)
    Call<Trainee[]> getAllTrainees();

    @GET(TRAINEES + "/{id}")
    Call<Trainee> getAllTrainees(@Path("id") Object id);

    @POST(TRAINEES)
    Call<Trainee> createTrainees(@Body Trainee trainee);

    @PUT(TRAINEES + "/{id}")
    Call<Trainee> updateTrainees(@Path("id") Object id, @Body Trainee trainee);

    @DELETE(TRAINEES + "/{id}")
    Call<Void> deleteTrainees(@Path("id") Object id);
}
