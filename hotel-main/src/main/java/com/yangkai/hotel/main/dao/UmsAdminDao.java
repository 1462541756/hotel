package com.yangkai.hotel.main.dao;

import com.yangkai.hotel.main.dto.RegisterParam;
import org.springframework.stereotype.Repository;

@Repository
public interface UmsAdminDao {

    int selectByUsername(String username);

    int insert(RegisterParam registerParam);
}
