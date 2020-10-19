package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.main.dto.RegisterParam;

public interface UmsAdminDao {

    int selectByUsername(String username);

    int insert(RegisterParam registerParam);
}
