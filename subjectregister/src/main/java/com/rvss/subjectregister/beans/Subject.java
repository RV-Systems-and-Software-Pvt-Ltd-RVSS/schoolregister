package com.rvss.subjectregister.beans;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subject {
    @Id

    private String subjectCode;           // Maps to `subject_code` varchar(10)
    private String subjectName;           // Maps to `subject_name` varchar(20)
    private boolean subjectXII;              // Maps to `subject_XII` binary(1)
    private boolean subjectX;                // Maps to `subject_X` binary(1)
    private boolean subjectIB;               // Maps to `subject_IB` binary(1)
    private boolean subjectGeneralClass; // Maps to `subject_general_class` char(1)
}
