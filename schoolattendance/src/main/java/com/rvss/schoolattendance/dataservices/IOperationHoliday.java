package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Holiday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IOperationHoliday {
    public ResponseEntity insertNewHoliday(Holiday holiday, HolidayJPARepository holidayJPARepository);
    public ResponseEntity<List> getAllHolidayBySchool(String schoolId, HolidayJPARepository holidayJPARepository);
    public ResponseEntity<List> getAllHolidayByRegion(String region, HolidayJPARepository holidayJPARepository);
    public ResponseEntity<Holiday> getHolidayDetailByDate (LocalDate date,HolidayJPARepository holidayJPARepository);
    public ResponseEntity<Holiday> getHolidayDetailByName (String holidayName,HolidayJPARepository holidayJPARepository);
    public ResponseEntity<List> getAllHolidayByType(String type, HolidayJPARepository holidayJPARepository);
}
