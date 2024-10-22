package com.exercise.lab11.api;

import com.exercise.lab11.model.Trainee;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// Interface defining various HTTP methods (GET, POST, PUT, DELETE) for the trainees
public interface TraineeService {
    String TRAINEES = "Nhanvien"; // Path for resource API requests

    @GET(TRAINEES)
    Call<Trainee[]> getAllTrainees(); // Get a list of all trainees

    @GET(TRAINEES + "/{id}")
    Call<Trainee> getAllTrainees(@Path("id") Object id); // Get details of a single trainee by ID

    @POST(TRAINEES)
    Call<Trainee> createTrainees(@Body Trainee trainee); // Create a new trainee

    @PUT(TRAINEES + "/{id}")
    Call<Trainee> updateTrainees(@Path("id") Object id, @Body Trainee trainee); // Update trainee details by ID

    @DELETE(TRAINEES + "/{id}")
    Call<Void> deleteTrainees(@Path("id") Object id); // Delete trainee by ID
}
