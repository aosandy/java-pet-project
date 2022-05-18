package server.service;

import server.entity.Auto;
import server.entity.AutoPersonnel;

import java.util.List;

public interface AutoService {

    List<Auto> allAutos();

    Auto createAuto(Auto auto);

    Auto updateAuto(Long id, Auto auto);

    void deleteAuto(Long id);

    Auto findAuto(Long id);

    List<Auto> findByPersonnelId(AutoPersonnel personnelId);

    int updatePersonnelById(Long id, AutoPersonnel personnelId);
}
