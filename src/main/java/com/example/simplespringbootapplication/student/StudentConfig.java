package com.example.simplespringbootapplication.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {

            Student bond = new Student(
                    "James Bond",
                    "james@gmail.com",
                    LocalDate.of(1998, Month.FEBRUARY, 19)
            );

            Student kelvin = new Student(
                    "Kelvin Heart",
                    "kelvin@gmail.com",
                    LocalDate.of(1996, Month.FEBRUARY, 20)
            );

            repository.saveAll(List.of(bond, kelvin));



        };
    }
}
