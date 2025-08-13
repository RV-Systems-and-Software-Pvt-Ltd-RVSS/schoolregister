package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Attendance;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface IOperationSchoolAttendance {
    public ResponseEntity insertNewAttendance(
            Attendance attendance,
            AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getAttendanceByschoolIdDate(
            String schoolId, LocalDate date,
            AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getAttendanceByregionDate(
            String region,LocalDate date,
            AttendanceJPARepository attendanceJPARepository);
    public ResponseEntity<List> getAttendanceByschoolNotHoliday(
            String  schoolId, Boolean isHoliday, AttendanceJPARepository attendanceJPARepository);
    public ResponseEntity<List> getBySchoolIdAndDateRange(String schoolId,
                                                          LocalDate startDate,
                                                          LocalDate endDate,
                                                          AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getAttendanceByClassAndDate(
            String classId,
            LocalDate date,
            AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getAttendanceByClassAndDateRange(
            String classId,
            LocalDate startDate,
            LocalDate endDate,
            AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getAttendanceByStudentAndDateRange(
            String studentId,
            LocalDate startDate,
            LocalDate endDate,
            AttendanceJPARepository attendanceJPARepository);

    public ResponseEntity<List> getByRegionAndDateRange(String region,
                                                        LocalDate startDate,
                                                        LocalDate endDate ,
                                                        AttendanceJPARepository attendanceJPARepository);
}
