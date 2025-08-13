package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Holiday;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IOperationHolidayIMPL implements IOperationHoliday{
    @Override
    public ResponseEntity insertNewHoliday(Holiday holiday, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(holidayJPARepository.save(holiday));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllHolidayBySchool(String schoolId, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(holidayJPARepository.findHolidayByschoolId(schoolId));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllHolidayByRegion(String region, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(holidayJPARepository.findHolidayByregion(region));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getHolidayDetailByDate(LocalDate date, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(holidayJPARepository.findHolidayBydate(date));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getHolidayDetailByName(String holidayName, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(holidayJPARepository.findHolidayByholidayName(holidayName));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllHolidayByType(String type, HolidayJPARepository holidayJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(holidayJPARepository.findHolidayBytype(type));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
}
