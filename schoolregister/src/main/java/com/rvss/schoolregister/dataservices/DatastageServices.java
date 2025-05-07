package com.rvss.schoolregister.dataservices;

import com.rvss.schoolregister.beans.Schools;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class DatastageServices {

    public static  List<Schools> getSchoolDetailsOnName(String schoolName,
                                          SchoolRepository schoolRepository,
                                          IOperationSchools iOperationSchools ){
        Predicate<Schools> schoolsPredicate = sch -> sch.getSchool_name().contains(schoolName);
        List<Schools> schoolList = iOperationSchools.getAllSchool(schoolRepository).stream()
                .filter(schoolsPredicate)
                .toList();

        /*List<Schools> list = schoolList.stream()
                .filter(schoolsPredicate)
                .collect(Collectors.toList());*/
           return schoolList;
    }

    public Schools getSchoolDetailsOnId(String id,SchoolRepository schoolRepository){
        return null;
    }


    }


