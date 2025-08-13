package com.rvss.schoolattendance.controller;

import com.rvss.schoolattendance.beans.Attendance;
import com.rvss.schoolattendance.beans.Holiday;
import com.rvss.schoolattendance.dataservices.AttendanceJPARepository;
import com.rvss.schoolattendance.dataservices.HolidayJPARepository;
import com.rvss.schoolattendance.dataservices.IOperationHoliday;
import com.rvss.schoolattendance.dataservices.IOperationSchoolAttendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/schoolattendance/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableJpaRepositories
public class DataInfoController {
    @Autowired
    IOperationHoliday iOperationHoliday;
    @Autowired
    HolidayJPARepository holidayJPARepository;
    @Autowired
    IOperationSchoolAttendance iOperationSchoolAttendance;
    @Autowired
    AttendanceJPARepository attendanceJPARepository;

    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Attendance Service is Up");
    }

    @PostMapping("/insertNewHoliday")
    public ResponseEntity insertNewClass(@Valid @RequestBody Holiday holiday) {
        ResponseEntity re = iOperationHoliday.insertNewHoliday(holiday,holidayJPARepository);
        return re;
    }

    @GetMapping("/bySchool/{schoolId}")
    public ResponseEntity<List> getAllHolidayBySchool(@PathVariable String schoolId) {
        return iOperationHoliday.getAllHolidayBySchool(schoolId, holidayJPARepository);
    }

    @GetMapping("/byRegion/{region}")
    public ResponseEntity<List> getAllHolidayByRegion(@PathVariable String region) {
        return iOperationHoliday.getAllHolidayByRegion(region, holidayJPARepository);
    }

    @GetMapping("/byDate")
    public ResponseEntity<Holiday> getHolidayDetailByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iOperationHoliday.getHolidayDetailByDate(date, holidayJPARepository);
    }

    @GetMapping("/byName/{holidayName}")
    public ResponseEntity<Holiday> getHolidayDetailByName(@PathVariable String holidayName) {
        return iOperationHoliday.getHolidayDetailByName(holidayName, holidayJPARepository);
    }

    @GetMapping("/byType/{type}")
    public ResponseEntity<List> getAllHolidayByType(@PathVariable String type) {
        return iOperationHoliday.getAllHolidayByType(type, holidayJPARepository);
    }

    // 1. Insert new attendance
    @PostMapping("/insertNewAttendance")
    public ResponseEntity insertNewAttendance(@Valid @RequestBody Attendance attendance) {
        return iOperationSchoolAttendance.insertNewAttendance(attendance, attendanceJPARepository);
    }

    // 2. Get attendance by schoolId & date
    @GetMapping("/getAttendanceByschoolIdDate/{schoolId}/date/{date}")
    public ResponseEntity<List> getAttendanceByschoolIdDate(
            @PathVariable String schoolId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iOperationSchoolAttendance.getAttendanceByschoolIdDate(schoolId, date, attendanceJPARepository);
    }

    // 3. Get attendance by region & date
    @GetMapping("/getAttendanceByregionDate/{region}/date/{date}")
    public ResponseEntity<List> getAttendanceByregionDate(
            @PathVariable String region,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iOperationSchoolAttendance.getAttendanceByregionDate(region, date, attendanceJPARepository);
    }

    // 4. Get attendance by schoolId & holiday flag
    @GetMapping("/getAttendanceByschoolNotHoliday/{schoolId}/holiday/{isHoliday}")
    public ResponseEntity<List> getAttendanceByschoolNotHoliday(
            @PathVariable String schoolId,
            @PathVariable Boolean isHoliday) {
        return iOperationSchoolAttendance.getAttendanceByschoolNotHoliday(schoolId, isHoliday, attendanceJPARepository);
    }

    // 5. Get attendance by schoolId & date range
    @GetMapping("/getBySchoolIdAndDateRange/{schoolId}/range")
    public ResponseEntity<List> getBySchoolIdAndDateRange(
            @PathVariable String schoolId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return iOperationSchoolAttendance.getBySchoolIdAndDateRange(schoolId, startDate, endDate, attendanceJPARepository);
    }

    // 6. Get attendance by classId & date
    @GetMapping("/getAttendanceByClassAndDate/{classId}/date/{date}")
    public ResponseEntity<List> getAttendanceByClassAndDate(
            @PathVariable String classId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return iOperationSchoolAttendance.getAttendanceByClassAndDate(classId, date, attendanceJPARepository);
    }

    // 7. Get attendance by classId & date range
    @GetMapping("/getAttendanceByClassAndDateRange/{classId}/range")
    public ResponseEntity<List> getAttendanceByClassAndDateRange(
            @PathVariable String classId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return iOperationSchoolAttendance.getAttendanceByClassAndDateRange(classId, startDate, endDate, attendanceJPARepository);
    }

    // 8. Get attendance by studentId & date range
    @GetMapping("/getAttendanceByStudentAndDateRange/{studentId}/range")
    public ResponseEntity<List> getAttendanceByStudentAndDateRange(
            @PathVariable String studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return iOperationSchoolAttendance.getAttendanceByStudentAndDateRange(studentId, startDate, endDate, attendanceJPARepository);
    }

    // 9. Get attendance by region & date range
    @GetMapping("/getByRegionAndDateRange/{region}/range")
    public ResponseEntity<List> getByRegionAndDateRange(
            @PathVariable String region,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return iOperationSchoolAttendance.getByRegionAndDateRange(region, startDate, endDate, attendanceJPARepository);
    }

}
