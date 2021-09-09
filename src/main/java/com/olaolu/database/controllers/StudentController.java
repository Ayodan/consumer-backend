package com.olaolu.database.controllers;

import com.olaolu.database.model.Student;
import com.olaolu.database.serviceAndDao.StudentRepoInterface;
import com.olaolu.database.serviceAndDao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.validation.Valid;

/**
 * @author akano.olanrewaju  @on 04/10/2019
 */
@Controller
@RequestMapping("/students/")
public class StudentController {
    private final StudentRepoInterface studentRepository;
        @Autowired
        public StudentController(StudentRepoInterface studentRepository) {
            this.studentRepository = studentRepository;
        }

        @GetMapping("signup")
        public String showSignUpForm(Student student) {
            return "add-student";
        }

        @GetMapping("list")
        public String showUpdateForm(Model model) {
            model.addAttribute("students", null);
            return "index";
        }

        @PostMapping("add")
        public String addStudent(@Valid Student student, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "add-student";
            }
            studentRepository.save(student);
            return "redirect:list";
        }

        @GetMapping("edit/{id}")
        public String showUpdateForm(@PathVariable("id") long id, Model model) {
            Student student = studentRepository.findById(id);
            model.addAttribute("student", student);
            return "update-student";
        }

        @PostMapping("update/{id}")
        public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
                                    Model model) {
            if (result.hasErrors()) {
                student.setId(id);
                return "update-student";
            }
            studentRepository.save(student);
            model.addAttribute("students", studentRepository.findAll());
            return "index";
        }

        @GetMapping("delete/{id}")
        public String deleteStudent(@PathVariable("id") long id, Model model) {
            studentRepository.delete(id);
            model.addAttribute("students", studentRepository.findAll());
            return "index";
        }
    }

