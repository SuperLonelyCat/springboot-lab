package com.ming.it.controller;

import com.ming.it.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 11119638
 * @date 2021/6/30 11:01
 */
@RestController
@Slf4j
@RequestMapping("/rest")
public class UserRestController {

    /*
     * ElasticsearchOperations的两种实现：
     *      ElasticsearchTemplate - 基于Transport Client实现（Spring Data Elasticsearc 4.x.x已过期）
     *      ElasticsearchRestTemplate - 基于High Level REST Client实现
     * */
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @PostMapping("/get/byId/{uid}")
    public User getById(@PathVariable String uid) {
        if (StringUtils.isBlank(uid)) {
            return new User();
        }
        return elasticsearchRestTemplate.get(uid, User.class);
    }

    @PostMapping("/get/byIds")
    public List<User> getByIds(@RequestBody List<String> uids) {
        if (CollectionUtils.isEmpty(uids)) {
            return Collections.emptyList();
        }
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withIds(uids).build();
        // multiGet方法中查询条件需包含主键Id，否则报异常No Id define for Query
        return elasticsearchRestTemplate.multiGet(searchQuery, User.class);
    }

    @PostMapping("/get/all")
    public List<User> getAll() {
        final SearchHits<User> searchAll = elasticsearchRestTemplate.search(new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery()).build(), User.class);
        return searchAll.get().map(SearchHit::getContent).collect(Collectors.toList());
    }
}
