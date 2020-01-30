package com.scl.edu.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/26
 * Description:
 */
@Data
public class SubjectNestedVo {

    private String id;
    private String title;
    private List<SubjectVo> children = new ArrayList<>();
}