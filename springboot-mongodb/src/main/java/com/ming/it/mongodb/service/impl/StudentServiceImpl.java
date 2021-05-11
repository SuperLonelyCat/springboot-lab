package com.ming.it.mongodb.service.impl;

import com.ming.it.mongodb.constant.MongoConstant;
import com.ming.it.mongodb.dto.PageQueryDto;
import com.ming.it.mongodb.dto.StudentQueryDto;
import com.ming.it.mongodb.entity.Course;
import com.ming.it.mongodb.entity.Student;
import com.ming.it.mongodb.service.MongoApi;
import com.ming.it.mongodb.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 11119638
 * @date 2021/3/24 19:23
 */
@Service
public class StudentServiceImpl implements StudentService, MongoApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    /* ******************************* 【新增】 *********************************** */

    @Override
    public Student insert(Student student) {

        student.setCreateAt(new Date());
        student.setModifiedAt(new Date());
        return mongoTemplate.insert(student);
    }

    @Override
    public List<Student> batchInsertOne(List<Student> studentList) {

        studentList.forEach(student -> {
            student.setCreateAt(new Date());
            student.setModifiedAt(new Date());
        });
        return new ArrayList<>(mongoTemplate.insert(studentList, Student.class));
    }

    @Override
    public List<Student> batchInsertTwo(List<Student> studentList) {

        studentList.forEach(student -> {
            student.setCreateAt(new Date());
            student.setModifiedAt(new Date());
        });
        return new ArrayList<>(mongoTemplate.insertAll(studentList));
    }

    /* ******************************* 【删除】 *********************************** */

    @Override
    public long deleteById(String id) {

        return mongoTemplate.remove(buildQuery().addCriteria(eqWithId(id)), Student.class).getDeletedCount();
    }

    @Override
    public long batchDeleteById(List<String> idList) {

        return mongoTemplate.remove(buildQuery().addCriteria(in(MongoConstant.OBJECT_ID, idList)),
                Student.class).getDeletedCount();
    }

    /* ******************************* 【更新】 *********************************** */

    @Override
    public long updateOne(Student student) {

        student.setModifiedAt(new Date());
        return mongoTemplate.updateFirst(buildQuery().addCriteria(eqWithId(student.getId())), buildUpdatedData(student),
                Student.class).getModifiedCount();
    }

    @Override
    public long updateAll(Student student) {

        student.setModifiedAt(new Date());
        return mongoTemplate.updateMulti(buildQuery().addCriteria(eqWithId(student.getId())), buildUpdatedData(student),
                Student.class).getModifiedCount();
    }


    @Override
    public long batchUpdate(List<Student> studentList) {

        // 增删改查都可批量构造
        List<Pair<Query, Update>> pairsList = new ArrayList<>();
        studentList.forEach(student -> {
            student.setModifiedAt(new Date());
            pairsList.add(Pair.of(buildQuery().addCriteria(eqWithId(student.getId())), buildUpdatedData(student)));
        });
        final BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Student.class);
        bulkOperations.updateOne(pairsList);
        return bulkOperations.execute().getModifiedCount();
    }

    /* ******************************* 【单表查询】 *********************************** */

    @Override
    public Student findById(String id) {

        return mongoTemplate.findById(id, Student.class);
    }

    @Override
    public Student findByStudentCode(String studentCode) {

        return mongoTemplate.findOne(buildQuery().addCriteria(eq(MongoConstant.STUDENT_CODE, studentCode)),
                Student.class);
    }

    @Override
    public List<Student> findByCourseCode(String courseCode) {

        return mongoTemplate.find(buildQuery().addCriteria(eq(MongoConstant.COURSE_CODE, courseCode)),
                Student.class);
    }

    @Override
    public List<Student> findByCourseCodeInPage(PageQueryDto pageQueryDto) {

        final Query query = buildQuery().addCriteria(eq(MongoConstant.COURSE_CODE, pageQueryDto.getKeyword()));
        query.with(PageRequest.of(pageQueryDto.getPageNo() - 1, pageQueryDto.getPageSize()));
        query.with(Sort.by(Sort.Order.asc(pageQueryDto.getOrderStr())));
        return null;
    }

    @Override
    public List<Student> findByStudentName(String studentName) {

        return mongoTemplate.find(buildQuery().addCriteria(like(MongoConstant.STUDENT_NAME, studentName)),
                Student.class);
    }

    @Override
    public List<Student> findByMultiCondition(StudentQueryDto studentQueryDto) {

        final List<Criteria> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(studentQueryDto.getStudentCode())) {
            list.add(eq(MongoConstant.STUDENT_CODE, studentQueryDto.getStudentCode()));
        }
        if (StringUtils.isNotEmpty((studentQueryDto.getKeyword()))) {
            list.add(new Criteria().orOperator(like(MongoConstant.STUDENT_NAME, studentQueryDto.getKeyword()),
                    like(MongoConstant.COURSE_NAME, studentQueryDto.getKeyword())));
        }
        if (StringUtils.isNotEmpty(studentQueryDto.getCourseCode())) {
            list.add(eq(MongoConstant.COURSE_CODE, studentQueryDto.getCourseCode()));
        }

        return mongoTemplate.find(buildQuery().addCriteria(new Criteria().andOperator(list.toArray(new Criteria[0]))),
                Student.class);
    }

    /* ******************************* 【多表查询】 *********************************** */

    @Override
    public List<Student> findStudentByCourseNameInPage(PageQueryDto pageQueryDto) {

        // 设置从表
        final String courseCollectionName = mongoTemplate.getCollectionName(Course.class);
        final LookupOperation courseCollection = LookupOperation.newLookup()
                .from(courseCollectionName)
                .localField(MongoConstant.COURSE_CODE)
                .foreignField(MongoConstant.COURSE_CODE)
                .as(courseCollectionName);

        // 分隔符
        final String SEPARATOR = ".";
        // 匹配条件
        final MatchOperation match = Aggregation.match(like(courseCollectionName + SEPARATOR +
                        MongoConstant.COURSE_NAME, pageQueryDto.getKeyword()));
        final UnwindOperation unwind = Aggregation.unwind(courseCollectionName);
        final SortOperation sort = Aggregation.sort(Sort.Direction.DESC, pageQueryDto.getOrderStr());
        final SkipOperation skip = Aggregation.skip(pageQueryDto.getPageOffset().longValue());
        final LimitOperation limit = Aggregation.limit(pageQueryDto.getPageSize());
        final Aggregation aggregation = Aggregation.newAggregation(courseCollection, unwind, match, sort, skip, limit);

        // 获取结果
        return mongoTemplate.aggregate(aggregation, Student.class, Student.class).getMappedResults();
    }
}
