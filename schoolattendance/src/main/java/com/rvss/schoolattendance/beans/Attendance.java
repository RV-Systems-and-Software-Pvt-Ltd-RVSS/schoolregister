package com.rvss.schoolattendance.beans;
import com.rvss.schoolattendance.beans.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {

    @Id
    private String attendanceId;  // e.g., "SCH101_CLASSA_20250801"
    private String schoolId;
    private String classId;

    private LocalDate date;       // yyyy-MM-dd
    private int dayOfYear;        // e.g., 213 for Aug 1
    private String day;           // e.g., "Monday"
    private String region;
    private boolean isHoliday;    // true if it's a holiday

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "attendance_student_status",
            joinColumns = @JoinColumn(name = "attendance_id")
    )
    @MapKeyColumn(name = "student_id")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Map<String, AttendanceStatus> studentAttendance; // studentId -> status
}
