package com.after00.neo4j.repository;

import com.after00.neo4j.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
    Person findByName(String name);
    Person findByBorn(String born);
    /**
     * 查询所有节点
     * @return
     */
    @Query("match (n:Person) return n;")
    List<Person> getAllPersonNode();


//    /**
//   * 创建节点
//   * @param userId
//   * @param name
//   * @param age
//   */
//  @Query("create (:Student {userId:{userId},name:{name},age:{age}})")
//  void createNode(@Param("userId") long userId, @Param("name") String name, @Param("age") long age);
//
//  /**
//   * 查询所有节点
//   * @return
//   */
//  @Query("match (n:Student) return n;")
//  List<Student> getAllStudentNode();
//
//  /**
//   * 查询所有节点的所有关系
//   * @return
//   */
//  @Query("match (a:Student)-[*1..]->(b:Student)return a,b;")
//  List<Student> getAllStudentNodeRelation();
//
//  /**
//   * 创建2节点的关系 上下关系
//   * @param superUserId
//   * @param lowerUserId
//   * @param time
//   * @return
//   */
//  @Query("match (a:Student),(b:ResellerRelation) where a.userId = {superUserId} and b.userId = {lowerUserId} create (a) - [r:RESELLER_ACTED_IN{since:{time}}] -> (b) return a,b;")
//  List<Student> addStudentNodeReseller(@Param("superUserId") long superUserId, @Param("lowerUserId") long lowerUserId, @Param("time") long time);
//  /**
//   * 根据id查询单个节点
//   * @param userId
//   * @return
//   */
//  @Query("match (n:Student) where n.userId ={userId} return n")
//  Student findByUserId(@Param("userId") long userId);
//
//  /**
//   * 根据userId查询节点直属 2级关系
//   * @param userId
//   * @return
//   */
//  @Query("match (a:Student)-[*1..1]->(b:Student) where a.userId = {userId} return a,b")
//  List<Student> findAllDirectlyByUserId(@Param("userId") long userId);
//
//  /**
//   * 根据userId 查询该节点的所有关系
//   * @param userId
//   * @return
//   */
//  @Query("match (a:Student)-[*1..]->(b:Student) where a.userId = {userId} return a,b")
//  List<Student> findAllStudentByUserId(@Param("userId") long userId);
//
//  /**
//   * 根据userId 修改节点 属性
//   * @param userId
//   * @param name
//   * @return
//   */
//  @Query("match (n:Student) where n.userId = {userId} set n.name ={name} return n")
//  Student updateStudentNode(@Param("userId") long userId, @Param("name") long name);
//
//
//  /**
//   * 删除节点关系
//   * @param superUserId
//   * @param lowerUserId
//   */
//  @Query("match (n1:Student),(n2:Student) where n1.userId={superUserId} AND n2.userId={lowerUserId} optional match (n1)-[r]-(n2) delete r;")
//  void deleteStudentNodeRelation(@Param("superUserId") long superUserId, @Param("lowerUserId") long lowerUserId);
}