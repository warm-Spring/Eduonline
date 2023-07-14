package com.xidian.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xidian.serviceedu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xidian.serviceedu.entity.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-06-08
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}

