package server.service;

import server.entity.Auto;
import server.entity.Journal;
import server.entity.Route;
import server.exception.EntityNotFoundException;
import server.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> allJournals() {
        return (List<Journal>) journalRepository.findAll();
    }

    @Override
    public Journal createJournal(Journal journal) {
        Journal copy = new Journal(
                journal.getTimeOut(),
                journal.getTimeIn()
        );
        return journalRepository.save(copy);
    }

    @Override
    public Journal updateJournal(Long id, Journal journal) {
        Journal copy = new Journal(
                journal.getTimeOut(),
                journal.getTimeIn()
        );
        copy.setId(id);
        return journalRepository.save(copy);
    }

    @Override
    public void deleteJournal(Long id) {
        journalRepository.deleteById(id);
    }

    @Override
    public Journal findJournal(Long id) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        if (optionalJournal.isPresent()) {
            return optionalJournal.get();
        } else {
            throw new EntityNotFoundException("Journal not found");
        }
    }

    @Override
    public boolean existsByRouteId(Route routeId) {
        return journalRepository.existsByRouteId(routeId);
    }

    @Override
    public boolean existsByAutoId(Auto autoId) {
        return journalRepository.existsByAutoId(autoId);
    }

    @Override
    public Long countByRouteId(Route routeId) {
        return journalRepository.countByRouteId(routeId);
    }
}
