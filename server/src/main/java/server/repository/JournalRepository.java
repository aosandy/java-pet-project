package server.repository;

import server.entity.Auto;
import server.entity.Journal;
import server.entity.Route;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends CrudRepository<Journal, Long> {

    @Query("select (count(j) > 0) from Journal j where j.routeId = ?1")
    boolean existsByRouteId(Route routeId);

    @Query("select (count(j) > 0) from Journal j where j.autoId = ?1")
    boolean existsByAutoId(Auto autoId);

    @Query("select count(j) from Journal j where j.routeId = ?1")
    long countByRouteId(Route routeId);
}
