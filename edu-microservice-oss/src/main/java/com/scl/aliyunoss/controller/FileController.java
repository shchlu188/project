package com.scl.aliyunoss.controller;

import com.scl.aliyunoss.service.FileService;
import com.scl.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.PastOrPresent;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/25
 * Description:
 */
@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public ResultVO upload(@ApiParam(name = "file",value = "文件",required = true)
                           @RequestParam("file")MultipartFile file){
        String uploadUrl = fileService.uploadFile(file);

        return ResultVO.ok().message("文件上传成功").data("url",uploadUrl);

    }
}
