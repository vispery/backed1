package com.example.backend.model;

import java.util.Date;
import java.util.List;

public interface ShortRentDao {

    void saveShortRent(ShortRent shortRent);

    void deleteShortRent(String id);

    void updateStartDate(String id, Date startDate);

    void updateEndDate(String id, Date endDate);

    void updatePass(String id, Boolean pass);

    void updateReview(String id, String review);

    void updatePayed(String id, boolean payed);

    void updateEnd(String id, boolean end);

    ShortRent getShortRentById(String id);

    List<ShortRent> getShortRentsByTenantId(String tenantId);

    List<ShortRent> getShortRentsByRoomId(String roomId);
}
