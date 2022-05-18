package server.repository;

import server.entity.Journal;
import server.entity.Route;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {

    @Transactional
    @Modifying
    @Query("delete from Route r where r.journals = ?1")
    int deleteByJournal(Journal journal);
}
