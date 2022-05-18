package server.service;

import server.entity.Journal;
import server.entity.Route;
import server.exception.EntityNotFoundException;
import server.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public List<Route> allRoutes() {
        return (List<Route>) routeRepository.findAll();
    }

    @Override
    public Route createRoute(Route route) {
        Route copy = new Route(
                route.getName()
        );
        return routeRepository.save(copy);
    }

    @Override
    public Route updateRoute(Long id, Route route) {
        Route copy = new Route(
                route.getName()
        );
        copy.setId(id);
        return routeRepository.save(copy);
    }

    @Override
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public Route findRoute(Long id) {
        Optional<Route> optionalRoute = routeRepository.findById(id);
        if (optionalRoute.isPresent()) {
            return optionalRoute.get();
        } else {
            throw new EntityNotFoundException("Route not found");
        }
    }

    @Override
    public int deleteByJournal(Journal journal) {
        return routeRepository.deleteByJournal(journal);
    }
}