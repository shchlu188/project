package com.scl.edu.controller.admin;

import com.scl.common.vo.ResultVO;
import com.scl.edu.service.FileService;
import com.scl.edu.util.ConstantPropertiesUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/25
 * Description:
 */
@Api(description = "阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public ResultVO upload(@ApiParam(name = "file", value = "文件", required = true)
                           @RequestParam("file") MultipartFile file,
                           @ApiParam(name = "host", value = "文件上传路径", required = false)
                           @RequestParam(value = "host", required = false) String host) {
        if(!StringUtils.isEmpty(host)){
            ConstantPropertiesUtil.FILE_HOST = host;
        }
        String uploadFile = fileService.uploadFile(file);
        return ResultVO.ok().message("文件上传成功").data("url", uploadFile);

    }
}
