package com.scl.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scl.common.util.ExcelImportUtil;
import com.scl.edu.entity.Subject;
import com.scl.edu.mapper.SubjectMapper;
import com.scl.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scl.edu.vo.SubjectNestedVo;
import com.scl.edu.vo.SubjectVO2;
import com.scl.edu.vo.SubjectVo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cl
 * @since 2020-01-17
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    /**
     *
     */
    @Autowired
    private SubjectMapper subjectMapper;

    @Transactional
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        // 错误消息列表
        List<String> errorMsg = new ArrayList<>();
        // 创建工具类对象
        ExcelImportUtil excelImportUtil = new ExcelImportUtil(file.getInputStream());
        // 获取工作表
        HSSFSheet sheet = excelImportUtil.getSheet();

        int rowCount = sheet.getPhysicalNumberOfRows();
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }

        for (int rowNum = 1; rowNum < rowCount; rowNum++) {
            HSSFRow rowData = sheet.getRow(rowCount);

            if (rowData != null) {
                String levelOneValue = "";
                HSSFCell levelOneCell = rowData.getCell(0);
                if (levelOneCell != null) {
                    levelOneValue = excelImportUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }
                //判断一级分类是否重复 TODO
                Subject subject = getByTitle(levelOneValue);
                String parentId;
                if (subject == null) {
                    //将一级分类存入数据库
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    baseMapper.insert(subjectLevelOne);//添加
                    parentId = subjectLevelOne.getParentId();
                }
                {
                    parentId = subject.getParentId();
                }
                //获取二级分类 TODO
                String levelTwoValue = "";
                HSSFCell levelTwoCell = rowData.getCell(1);
                if (levelTwoCell != null) {
                    levelTwoValue = excelImportUtil.getCellValue(levelTwoCell).trim();
                    if (StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }
                /*
                判断二级分类是否重复 TODO
                将二级分类存入数据库 TODO
                */
                Subject subjectSub = getSubbyTitle(levelTwoValue, parentId);
                Subject subjectLevelTwo;
                if (subjectSub == null) {
                    subjectLevelTwo = new Subject();
                    subjectLevelTwo.setParentId(parentId);
                    subjectLevelTwo.setSort(rowNum);
                    subject.setTitle(levelTwoValue);
                    baseMapper.insert(subject);
                }

            }

        }
        return errorMsg;
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<Subject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录 TODO
        QueryWrapper<Subject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<Subject> subSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        for (Subject subject : subjects) {
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            for (Subject subSubject : subSubjects) {

                if (subject.getId().equals(subSubject.getParentId())) {

                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }

        return subjectNestedVoArrayList;
    }

    @Override
    public List<SubjectVO2> nestedList2() {
        return subjectMapper.selectNestedListByParentId("0");

    }

    private Subject getSubbyTitle(String levelTwoValue, String parentId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", levelTwoValue);
        wrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(wrapper);

    }

    private Subject getByTitle(String title) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", "0");

        return baseMapper.selectOne(wrapper);
    }
}
