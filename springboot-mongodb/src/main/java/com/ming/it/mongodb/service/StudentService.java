package com.ming.it.mongodb.service;

import com.ming.it.mongodb.dto.PageQueryDto;
import com.ming.it.mongodb.dto.StudentQueryDto;
import com.ming.it.mongodb.entity.Student;

import java.util.List;

/**
 * @author 11119638
 * @date 2021/3/23 17:08
 */
public interface StudentService {

    /* ******************************* 【新增】 *********************************** */

    Student insert(Student student);

    List<Student> batchInsertOne(List<Student> studentList);

    List<Student> batchInsertTwo(List<Student> studentList);

    /* ******************************* 【删除】 *********************************** */

    long deleteById(String id);

    long batchDeleteById(List<String> idList);

    /* ******************************* 【更新】 *********************************** */

    long updateOne(Student student);

    long updateAll(Student student);

    long batchUpdate(List<Student> studentList);

    /* ****************************** 【单表查询】 ******************************* */

    Student findById(String id);

    Student findByStudentCode(String studentCode);

    List<Student> findByCourseCode(String courseCode);

    List<Student> findByCourseCodeInPage(PageQueryDto pageQueryBo);

    List<Student> findByStudentName(String studentName);

    List<Student> findByMultiCondition(StudentQueryDto studentBo);

    /* ****************************** 【多表查询】 ******************************* */

    List<Student> findStudentByCourseNameInPage(PageQueryDto pageQueryBo);
}
