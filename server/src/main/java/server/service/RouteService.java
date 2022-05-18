package server.service;

import server.entity.Journal;
import server.entity.Route;

import java.util.List;

public interface RouteService {

    List<Route> allRoutes();

    Route createRoute(Route route);

    Route updateRoute(Long id, Route route);

    void deleteRoute(Long id);

    Route findRoute(Long id);

    int deleteByJournal(Journal journal);

}
