//package com.after00.neo4j;
//
//import com.after00.neo4j.domain.Movie;
//import org.neo4j.ogm.annotation.Relationship;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class MoveAndPersonRelationshipUtils {
//    @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
//    public Set<Movie> actors;
//
//    public void addActor(Movie movie) {
//        if (actors == null) {
//            actors = new HashSet<>();
//        }
//        actors.add(movie);
//    }
//
//    @Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
//    public Set<Movie> directors;
//
//    public void addDirector(Movie movie) {
//        if (directors == null) {
//            directors = new HashSet<>();
//        }
//        directors.add(movie);
//    }
//}
