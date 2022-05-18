package server.service;

import server.entity.Auto;
import server.entity.AutoPersonnel;
import server.exception.EntityNotFoundException;
import server.repository.AutoPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoPersonnelServiceImpl implements AutoPersonnelService {

    @Autowired
    private AutoPersonnelRepository autoPersonnelRepository;

    @Override
    public List<AutoPersonnel> allAutoPersonnels() {
        return (List<AutoPersonnel>) autoPersonnelRepository.findAll();
    }

    @Override
    public AutoPersonnel createAutoPersonnel(AutoPersonnel autoPersonnel) {
        AutoPersonnel copy = new AutoPersonnel(
                autoPersonnel.getFirstName(),
                autoPersonnel.getLastName(),
                autoPersonnel.getPatherName()
        );
        return autoPersonnelRepository.save(copy);
    }

    @Override
    public AutoPersonnel updateAutoPersonnel(Long id, AutoPersonnel autoPersonnel) {
        AutoPersonnel copy = new AutoPersonnel(
                autoPersonnel.getFirstName(),
                autoPersonnel.getLastName(),
                autoPersonnel.getPatherName()
        );
        copy.setId(id);
        return autoPersonnelRepository.save(copy);
    }

    @Override
    public void deleteAutoPersonnel(Long id) {
        autoPersonnelRepository.deleteById(id);
    }

    @Override
    public AutoPersonnel findAutoPersonnel(Long id) {
        Optional<AutoPersonnel> optionalAutoPersonnel = autoPersonnelRepository.findById(id);
        if (optionalAutoPersonnel.isPresent()) {
            return optionalAutoPersonnel.get();
        } else {
            throw new EntityNotFoundException("Auto Personnel not found");
        }
    }

    @Override
    public AutoPersonnel findByAuto(Auto auto) {
        return autoPersonnelRepository.findByAuto(auto);
    }
}
