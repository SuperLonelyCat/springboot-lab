package com.ming.it.repository;

import com.ming.it.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 11119638
 * @date 2021/6/30 14:51
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    // 非字符串精准查询
    List<User> findByAge(Integer age);

    // 根据自定义方法约定
    // 注：findByName、findByNameIs以及findByNameEquals与效果相同, 查询字符串时模糊匹配查询
    List<User> findByNameEquals(String name);

    // 根据自定义方法约定
    // findByNameLike包含查询，只有address中包含待查询字符即可
    List<User> findByAddressLike(String address);
}
