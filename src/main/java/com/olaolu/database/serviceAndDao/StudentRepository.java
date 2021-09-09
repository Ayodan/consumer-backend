package com.olaolu.database.serviceAndDao;

import com.olaolu.database.model.Student;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author akano.olanrewaju  @on 04/10/2019
 */
@Service
public class StudentRepository implements StudentRepoInterface {
    RestTemplate restTemplate=new RestTemplate();
    List<Student> students= new ArrayList<>();
    @Override
    public List<Student> findAll() {

        students= (List<Student>) restTemplate.getForObject("http://localhost:7070/mycoolapp/students/list",List.class);


        return students;
    }

    @Override
    public Student findById(long id) {
        return students.get((int) id);
    }

    @Override
    public Student save(Student student) {
        restTemplate.postForObject("http://localhost:7070/mycoolapp/students/add",student,Student.class);
        return student;
    }

    @Override
    public void delete(long studentId) {
       String response= restTemplate.getForObject("http://localhost:7070/mycoolapp/students/delete/"+studentId,String.class);
    }
}
