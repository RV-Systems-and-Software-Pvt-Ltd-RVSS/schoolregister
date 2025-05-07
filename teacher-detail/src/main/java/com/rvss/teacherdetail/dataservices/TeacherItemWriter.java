package com.rvss.teacherdetail.dataservices;
import com.rvss.teacherdetail.beans.Teacher;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherItemWriter implements ItemWriter<Teacher> {

    @Autowired
    private TeacherJPARepository teacherJPARepository;
    @Override
    public void write(Chunk<? extends Teacher> items) throws Exception {
        for (Teacher teacher : items) {
            try {
                teacherJPARepository.save(teacher);
            } catch (Exception exp) {
                exp.printStackTrace(); // handle exception appropriately
            }
        }
    }
}
