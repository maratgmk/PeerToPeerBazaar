package org.gafiev.peertopeerbazaar.service;

import org.gafiev.peertopeerbazaar.entity.delivery.Address;
import org.gafiev.peertopeerbazaar.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional(readOnly = true)
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Set<Address> getAddressesByTown(String town) {
        return addressRepository.findAddressesByTown(town);
    }

    @Transactional(readOnly = true)
    public Set<Address> getAddressesByZipCode(int zipCode) {
        return addressRepository.findAddressesByZipCode(zipCode);
    }

    @Transactional(readOnly = true)
    public Set<Address> getAddressesByCoordinates(double latitude, double longitude) {
        return addressRepository.findAddressesByLatitudeAndLongitude(latitude, longitude);
    }

    @Transactional
    public long countAddressesInTown(String street) {
        return addressRepository.countByStreet(street);
    }

    @Transactional
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }


    @Transactional(readOnly = true)
    public Set<Address> getAddressesByStreet(String street) {
        return addressRepository.findByStreet(street);
    }

    @Transactional
    public Address updateAddress(Long id, Address addressDetails) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));

        address.setTown(addressDetails.getTown());
        address.setStreet(addressDetails.getStreet());
        address.setNumberBuilding(addressDetails.getNumberBuilding());
        address.setZipCode(addressDetails.getZipCode());
        address.setLatitude(addressDetails.getLatitude());
        address.setLongitude(addressDetails.getLongitude());
        address.setAttitude(addressDetails.getAttitude());
        address.setAccuracy(addressDetails.getAccuracy());

        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id " + id));
        addressRepository.delete(address);
    }

}
