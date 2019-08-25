package com.example.backend.model;

import java.util.Date;
import java.util.List;

public interface LongRentDao {

    void saveLongRent(LongRent longRent);

    void deleteLongRent(String id);

    void updateStartDate(String id, Date startDate);

    void updateEndDate(String id, Date endDate);

    void updatePass(String id, Boolean pass);

    void updateReview(String id, String review);

    /**
     * 修改长租记录中的付费情况，比如用户第一个月付款了，就把payed的第一个元素设为true
     * @param id 租房记录的id
     * @param payed 一般应该是true
     * @param num 第几个月，所以在具体实现接口的时候，需要把num-1
     */
    void updatePayed(String id, boolean payed, int num);

    void updateEnd(String id, boolean end);

    LongRent getLongRentById(String id);

    List<LongRent> getLongRentsByTenantId(String tenantId);

    List<LongRent> getLongRentsByRoomId(String roomId);
}
