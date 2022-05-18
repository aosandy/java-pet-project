package server.repository;

import server.entity.Auto;
import server.entity.AutoPersonnel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoPersonnelRepository extends CrudRepository<AutoPersonnel, Long> {

    @Query("select a from AutoPersonnel a where a.autos = ?1")
    AutoPersonnel findByAuto(Auto auto);
}
