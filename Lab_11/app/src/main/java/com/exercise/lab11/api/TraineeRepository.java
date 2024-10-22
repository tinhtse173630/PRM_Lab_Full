package com.exercise.lab11.api;

public class TraineeRepository {
    public static TraineeService getTraineeService() {
        return APIClient.getClient().create(TraineeService.class);
    }
}
