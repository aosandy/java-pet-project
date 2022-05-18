package server.repository;

import server.entity.Auto;
import server.entity.AutoPersonnel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AutoRepository extends CrudRepository<Auto, Long> {

    @Query("select a from Auto a where a.personnelId = ?1")
    List<Auto> findByPersonnelId(AutoPersonnel personnelId);

    @Transactional
    @Modifying
    @Query("update Auto a set a.id = ?1 where a.personnelId = ?2")
    int updatePersonnelById(Long id, AutoPersonnel personnelId);
}
