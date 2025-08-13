package com.rvss.schoolattendance.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Holiday {
    @Id
    private String holidayId;      // e.g., schoolId + "_" + date
    private String schoolId;
    private String region;
    private LocalDate date;        // yyyy-MM-dd
    private String dayOfYear;      //
    private String day;            // Monday,Tuesday
    private String holidayName;    // e.g., Diwali, Independence Day
    private String type;           // e.g., "National", "Weekend", "Custom"
}
