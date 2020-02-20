package com.ningmeng.manage_course.dao;

import com.ningmeng.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DictionaryRepository extends MongoRepository<SysDictionary,String> {
    SysDictionary findByDType(String type);

}
