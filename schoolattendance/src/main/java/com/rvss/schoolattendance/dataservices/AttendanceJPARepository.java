package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Attendance;
import com.rvss.schoolattendance.beans.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceJPARepository extends JpaRepository<Attendance, Long> {
    @Query("SELECT s FROM Attendance s WHERE s.schoolId = ?1 and s.date =?2" )
    List<Attendance> findAttendanceByschoolIdDate(String  schoolId, LocalDate date);

    @Query("SELECT s FROM Attendance s WHERE s.classId = ?1 and s.date =?2" )
    List<Attendance> findAttendanceByclassIdDate(String  classId, LocalDate date);

    @Query("SELECT s FROM Attendance s WHERE s.region = ?1 and s.date =?2" )
    List<Attendance> findAttendanceByregionDate(String  region, LocalDate date);

    @Query("SELECT s FROM Attendance s WHERE s.schoolId = ?1 and s.isHoliday =?2")
    List<Attendance> findAttendanceByschoolNotHoliday(String  schoolId, Boolean isHoliday);

    @Query("SELECT a FROM Attendance a WHERE a.classId = :classId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByClassIdAndDateRange(String classId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.schoolId = :schoolId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findBySchoolIdAndDateRange(String schoolId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.region = :region AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByRegionAndDateRange(String region, LocalDate startDate, LocalDate endDate);

    @Query("SELECT a FROM Attendance a JOIN a.studentAttendance sa " +
            "WHERE KEY(sa) = :studentId AND a.date BETWEEN :startDate AND :endDate")
    List<Attendance> findByStudentIdAndDateRange(String studentId, LocalDate startDate, LocalDate endDate);
}
