package com.example.backend.model;

public interface WorkerDao {

    void saveWorker(Worker worker);

    void deleteWorker(String id);

    void updateName(String id, String name);

    void updateImage(String id, String image);

    void updateMark(String id, double mark);

    Worker getWorkerById(String id);
}
