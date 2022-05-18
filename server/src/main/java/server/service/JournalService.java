package server.service;

import server.entity.Auto;
import server.entity.Journal;
import server.entity.Route;

import java.util.List;

public interface JournalService {

    List<Journal> allJournals();

    Journal createJournal(Journal journal);

    Journal updateJournal(Long id, Journal journal);

    void deleteJournal(Long id);

    Journal findJournal(Long id);

    boolean existsByRouteId(Route routeId);

    boolean existsByAutoId(Auto autoId);

    Long countByRouteId(Route routeId);
}
