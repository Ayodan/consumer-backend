package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author akano.olanrewaju  @on 04/10/2019
 */
@Service
public interface StudentRepoInterface {
    public List<Student> findAll();
    public Student findById(long id);
    public Student save(Student student);
    public void delete(long student);
}
