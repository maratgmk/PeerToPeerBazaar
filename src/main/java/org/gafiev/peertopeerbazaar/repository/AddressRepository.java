package org.gafiev.peertopeerbazaar.repository;

import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Optional<Address> findById(Long id);

    Set<Address> findAddressesByStreet(String street);

    Set<Address> findAddressesByTown(String town);

    Set<Address> findAddressesByZipCode(int zipCode);

    Set<Address> findAddressesByLatitudeAndLongitude(double latitude, double longitude);

    void deleteById(Long id);

    long countByStreet(String street);


    Set<Address> findByStreet(String street);

}
// TODO над каждым методом поставить @Transaction и в @Service