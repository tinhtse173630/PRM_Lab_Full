package com.exercise.lab11.api;

// Repository to get the trainee service interface
public class TraineeRepository {
    public static TraineeService getTraineeService() {
        return APIClient.getClient().create(TraineeService.class); // Creates the service from APIClient
    }
}
