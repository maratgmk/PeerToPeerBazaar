package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.delivery.Quadcopter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadcopterRepository extends JpaRepository<Quadcopter,Long> {

    Quadcopter findByLatitudeAndLongitude(double latitude, double longitude);

}
