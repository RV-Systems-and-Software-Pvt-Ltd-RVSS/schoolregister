package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Attendance;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IOperationSchoolAttendanceIMPL implements  IOperationSchoolAttendance{

    @Override
    public ResponseEntity insertNewAttendance(Attendance attendance, AttendanceJPARepository attendanceJPARepository) {
        Attendance savedAttendance = attendanceJPARepository.save(attendance);
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List> getAttendanceByClassAndDate(
            String classId,
            LocalDate date,
            AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findAttendanceByclassIdDate(classId,date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAttendanceByschoolIdDate(String schoolId,LocalDate date,AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findAttendanceByschoolIdDate(schoolId,date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAttendanceByregionDate(String region, LocalDate date,AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findAttendanceByregionDate(region,date);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAttendanceByschoolNotHoliday(String  schoolId, Boolean isHoliday, AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findAttendanceByschoolNotHoliday(schoolId,true);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getBySchoolIdAndDateRange(String schoolId, LocalDate startDate, LocalDate endDate, AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findBySchoolIdAndDateRange(schoolId,startDate,endDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List> getAttendanceByClassAndDateRange(String classId, LocalDate startDate, LocalDate endDate, AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findByClassIdAndDateRange(classId,startDate,endDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getAttendanceByStudentAndDateRange(String studentId, LocalDate startDate, LocalDate endDate, AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findByStudentIdAndDateRange(studentId,startDate,endDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List> getByRegionAndDateRange(String region, LocalDate startDate, LocalDate endDate , AttendanceJPARepository attendanceJPARepository) {
        List<Attendance> list = attendanceJPARepository.findByRegionAndDateRange(region,startDate,endDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
