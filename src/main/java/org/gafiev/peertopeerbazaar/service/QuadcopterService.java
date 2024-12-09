package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.delivery.Quadcopter;
import org.gafiev.peertopeerbazaar.repository.QuadcopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuadcopterService {
    private final QuadcopterRepository quadcopterRepository;

    @Autowired
    public QuadcopterService(QuadcopterRepository quadcopterRepository) {
        this.quadcopterRepository = quadcopterRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Quadcopter> findByLatitudeAndLongitude(double latitude, double longitude) {
        return Optional.ofNullable(quadcopterRepository.findByLatitudeAndLongitude(latitude, longitude));
    }

    @Transactional(readOnly = true)
    public Optional<Quadcopter> getQuadcopterById(Long id) {
        return quadcopterRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Quadcopter> getAllQuadcopters() {
        return quadcopterRepository.findAll();
    }

    @Transactional
    public Quadcopter createQuadcopter(Quadcopter quadcopter) {
        return quadcopterRepository.save(quadcopter);
    }

    @Transactional
    public Quadcopter updateQuadcopter(Long id, Quadcopter quadcopterDetails) {
        Quadcopter quadcopter = quadcopterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quadcopter not found with id " + id));

        quadcopter.setLatitude(quadcopterDetails.getLatitude());
        quadcopter.setLongitude(quadcopterDetails.getLongitude());
        quadcopter.setAttitude(quadcopterDetails.getAttitude());
        quadcopter.setAccuracy(quadcopterDetails.getAccuracy());
        quadcopter.setTimeStamp(quadcopterDetails.getTimeStamp());
        quadcopter.setSpeed(quadcopterDetails.getSpeed());
        quadcopter.setBearing(quadcopterDetails.getBearing());
        quadcopter.setDeliveries(quadcopterDetails.getDeliveries());

        return quadcopterRepository.save(quadcopter);
    }

    @Transactional
    public void deleteQuadcopter(Long id) {
        Quadcopter quadcopter = quadcopterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quadcopter not found with id " + id));
        quadcopterRepository.delete(quadcopter);
    }

}
