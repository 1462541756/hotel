package com.yangkai.hotel.main.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UmsResourceDao {

    int deleteRelated(Long id);
}
