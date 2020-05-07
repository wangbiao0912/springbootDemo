package com.after00.neo4j.repository;

import com.after00.neo4j.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findByTitle(String title);
}