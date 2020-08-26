//package com.after.neo4j;
//
//import com.after00.Application;
//import com.after00.neo4j.domain.Movie;
//import com.after00.neo4j.domain.Person;
//import com.after00.neo4j.repository.MovieRepository;
//import com.after00.neo4j.repository.PersonRepository;
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@Component
//public class test {
//    @Autowired
//    MovieRepository movieRepository;
//    @Autowired
//    PersonRepository personRepository;
//
//    /**
//     * 创建movie
//     */
//    @Test
//    public void testSaveMovie() {
//        Movie m1 = new Movie("无问西东", "2018");
//        Movie m2 = new Movie("罗曼蒂克消亡史", "2016");
//        movieRepository.save(m1);
//        movieRepository.save(m2);
//    }
//    /**
//     * 创建person及其与movie的关系
//     */
//    @Test
//    public void testSavePerson() {
////        personRepository.deleteAll();
//        Person p1 = new Person("章子怡", "1979");
//        Person p2 = new Person("李芳芳", "1976");
//        Person p3 = new Person("程耳", "1970");
//        Movie m1 = movieRepository.findByTitle("罗曼蒂克消亡史");
//        Movie m2 = movieRepository.findByTitle("无问西东");
//        if (m1!=null) {
//            p1.addActor(m1);
//            p3.addDirector(m1);
//        }
//        if (m2!=null) {
//            p3.addActor(m2);
//            p2.addDirector(m2);
//        }
//        personRepository.save(p1);
//        personRepository.save(p2);
//        personRepository.save(p3);
//    }
//
//    @Test
//    public void testfindByTitle() {
//        Movie movie = movieRepository.findByTitle("罗曼蒂克消亡史");
//        log.info(JSON.toJSONString(movie));
//    }
//    @Test
//    public void testfindByName() {
//
////        personRepository.deleteAll();
//        Person person = personRepository.findByName("程耳");
//        log.info(JSON.toJSONString(person));
//    }
//    @Test
//    public void testfindByName2() {
//        Person person = personRepository.findByBorn("1976");
//        log.info(JSON.toJSONString(person));
//    }
//    @Test
//    public void getAllStudentNode() {
//        List<Person> persons = personRepository.getAllPersonNode();
//        log.info(JSON.toJSONString(persons));
//    }
//
//}
