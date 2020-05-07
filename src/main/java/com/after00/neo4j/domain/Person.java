package com.after00.neo4j.domain;

import com.after00.neo4j.MoveAndPersonRelationshipUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;


@Data
@AllArgsConstructor
@NodeEntity
public class Person extends MoveAndPersonRelationshipUtils {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String born;
    public Person() {// 从 Neo4j API 2.0.5开始需要无参构造函数

    }
    public Person(String name, String born) {
        this.name = name;
        this.born = born;
    }

}