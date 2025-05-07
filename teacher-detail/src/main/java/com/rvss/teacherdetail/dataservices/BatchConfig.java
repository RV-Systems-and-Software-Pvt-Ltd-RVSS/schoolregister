package com.rvss.teacherdetail.dataservices;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.rvss.teacherdetail.beans.Teacher;
import com.rvss.teacherdetail.sftp.JavaSSHService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableBatchProcessing
public class BatchConfig  {

    @Autowired
    private DataSource dataSource;
    @Autowired
    TeacherJPARepository teacherJPARepository;

    @Autowired
    IOperationTeachers iOperationTeachers;

    @Autowired
    JobCompletionNotificationListener listener;
    @Autowired
    TeacherItemWriter teacherItemWriter;


    @Bean
    public Job importTeacherJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step step1) {
        return new JobBuilder("importUserJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws SftpException, IOException, JSchException {
        return new StepBuilder("step1", jobRepository)
                .<Teacher, Teacher> chunk(10, transactionManager)
                .reader(reader(null))
                .processor(processor())
                .writer(items -> { // Lambda expression for writing
                    for ( Teacher teacher : items) {
                        teacherJPARepository.save((Teacher) teacher);
                    }
                })
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<Teacher> reader(@Value("#{jobParameters['filename']}") String filename) throws IOException, SftpException, JSchException {
        System.out.println("File Name in Reader "+filename);
        Resource resource = new JavaSSHService().getSourceResource(filename);
        System.out.println("Resource class: " + resource.getClass().getName());
        System.out.println("Resource exists: " + resource.exists()+"  "+resource.contentLength());

            return new FlatFileItemReaderBuilder<Teacher>()
                    .name("teacherItemReader")
                    .resource(resource) // Fetches file from SFTP
                    .delimited()
                    .delimiter(",")
                    .names(new String[]{
                            "sl_no", "teacher_name", "teacher_address", "teacher_emp_id",
                            "teacher_designation", "teacher_qualification", "teacher_type",
                            "teacher_specialization", "teacher_phone_no", "teacher_email_address",
                            "teacher_is_in_service", "class_teacher", "school_id", "asst_class_teacher",
                            "teacher_core_subject"
                    })
                    .linesToSkip(1)
                    .fieldSetMapper(new BeanWrapperFieldSetMapper<Teacher>() {{
                        setTargetType(Teacher.class);
                    }})
                    .build();
    }



    @Bean
    public ItemProcessor<Teacher, Teacher> processor() {
        return item -> item; // No processing, pass-through
    }



}
