package server.service;

import server.entity.Auto;
import server.entity.AutoPersonnel;
import server.exception.EntityNotFoundException;
import server.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoServiceImpl implements AutoService {

    @Autowired
    private AutoRepository autoRepository;

    @Override
    public List<Auto> allAutos() {
        return (List<Auto>) autoRepository.findAll();
    }

    @Override
    public Auto createAuto(Auto auto) {
        Auto copy = new Auto(
                auto.getNum(),
                auto.getColor(),
                auto.getMark()
        );
        return autoRepository.save(copy);
    }

    @Override
    public Auto updateAuto(Long id, Auto auto) {
        Auto copy = new Auto(
                auto.getNum(),
                auto.getColor(),
                auto.getMark()
        );
        copy.setId(id);
        return autoRepository.save(copy);
    }

    @Override
    public void deleteAuto(Long id) {
        autoRepository.deleteById(id);
    }

    @Override
    public Auto findAuto(Long id) {
        Optional<Auto> optionalAuto = autoRepository.findById(id);
        if (optionalAuto.isPresent()) {
            return optionalAuto.get();
        } else {
            throw new EntityNotFoundException("Auto not found");
        }
    }

    @Override
    public List<Auto> findByPersonnelId(AutoPersonnel personnelId) {
        return autoRepository.findByPersonnelId(personnelId);
    }

    @Override
    public int updatePersonnelById(Long id, AutoPersonnel personnelId) {
        return autoRepository.updatePersonnelById(id, personnelId);
    }
}
