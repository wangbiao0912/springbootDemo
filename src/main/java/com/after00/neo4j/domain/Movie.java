//package com.after00.neo4j.domain;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.neo4j.ogm.annotation.GeneratedValue;
//import org.neo4j.ogm.annotation.Id;
//import org.neo4j.ogm.annotation.NodeEntity;
//
//@Data
//@AllArgsConstructor
//@NodeEntity
//public class Movie {
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String title;
//    private String released;
//
//    public Movie() {
//
//    }
//
//    public Movie(String title, String released) {
//        this.title = title;
//        this.released = released;
//    }
//}