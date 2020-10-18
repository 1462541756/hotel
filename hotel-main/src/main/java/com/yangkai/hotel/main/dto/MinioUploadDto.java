package com.yangkai.hotel.main.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传返回结果
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MinioUploadDto {
    private String url;
    private String name;
}
