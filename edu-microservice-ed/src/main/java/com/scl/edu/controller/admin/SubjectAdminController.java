package com.scl.edu.controller.admin;

import com.baomidou.mybatisplus.extension.api.R;
import com.scl.common.constants.ResultCodeEnum;
import com.scl.common.exception.SclException;
import com.scl.common.vo.ResultVO;
import com.scl.edu.service.SubjectService;
import com.scl.edu.vo.SubjectNestedVo;
import com.scl.edu.vo.SubjectVO2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/26
 * Description:
 */
@Api(description = "课程分类管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/edu/subject")
@Slf4j
public class SubjectAdminController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public ResultVO batchImport(@ApiParam(name = "file", value = "Excel文件", required = true)
                                @RequestParam("file") MultipartFile file){
        try {
            List<String> errorMsg = subjectService.batchImport(file);
            if (errorMsg.size() == 0){
                return ResultVO.ok().message("批量导入成功");
            }else {
                return ResultVO.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SclException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }
    // 403错误，可能是因为路径写错误，检查
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("list")
    public ResultVO nestedList(){

        List<SubjectNestedVo> subjectNestedVoList = subjectService.nestedList();
        return ResultVO.ok().data("items", subjectNestedVoList);
    }
    /*
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("list2")
    public ResultVO nestedList2(){

        List<SubjectVO2> subjectNestedVoList = subjectService.nestedList2();
        return ResultVO.ok().data("items", subjectNestedVoList);
    }*/
}
