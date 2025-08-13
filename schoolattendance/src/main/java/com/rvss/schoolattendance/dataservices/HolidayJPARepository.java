package com.rvss.schoolattendance.dataservices;

import com.rvss.schoolattendance.beans.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HolidayJPARepository extends JpaRepository<Holiday, Long> {

    @Query("SELECT s FROM Holiday s WHERE s.schoolId = ?1")
    List<Holiday> findHolidayByschoolId(String  schoolId);

    @Query("SELECT s FROM Holiday s WHERE s.region = ?1")
    List<Holiday> findHolidayByregion(String  region);

    @Query("SELECT s FROM Holiday s WHERE s.date = ?1")
    Holiday findHolidayBydate(LocalDate date);

    @Query("SELECT s FROM Holiday s WHERE s.dayOfYear = ?1")
    Holiday findHolidayBydayOfYear(String  dayOfYear);

    @Query("SELECT s FROM Holiday s WHERE s.holidayName = ?1")
    Holiday findHolidayByholidayName(String  holidayName);

    @Query("SELECT s FROM Holiday s WHERE s.type = ?1")
    List<Holiday> findHolidayBytype(String  type);
}
