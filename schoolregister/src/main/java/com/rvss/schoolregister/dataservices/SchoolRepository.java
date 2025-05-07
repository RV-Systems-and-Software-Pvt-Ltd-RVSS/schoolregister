package com.rvss.schoolregister.dataservices;

import com.rvss.schoolregister.beans.Schools;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SchoolRepository extends MongoRepository<Schools, String> {
    @Query("{school_name:'?0'}")
    Schools findByName(String name);

    @Query("{school_id:'?0'}")
    Schools getSchoolById(String id);
}
