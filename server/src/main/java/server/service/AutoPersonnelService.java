package server.service;

import server.entity.Auto;
import server.entity.AutoPersonnel;

import java.util.List;

public interface AutoPersonnelService {

    List<AutoPersonnel> allAutoPersonnels();

    AutoPersonnel createAutoPersonnel(AutoPersonnel autoPersonnel);

    AutoPersonnel updateAutoPersonnel(Long id, AutoPersonnel autoPersonnel);

    void deleteAutoPersonnel(Long id);

    AutoPersonnel findAutoPersonnel(Long id);

    AutoPersonnel findByAuto(Auto auto);
}
