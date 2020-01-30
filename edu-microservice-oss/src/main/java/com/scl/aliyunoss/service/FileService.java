package com.scl.aliyunoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/24
 * Description:
 */
public interface FileService {
    String uploadFile(MultipartFile multipartFile);
}
