package com.yangkai.hotel.main.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UmsMenuDao {

    int deleteRelated(Long id);

}
