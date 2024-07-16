package gr.aueb.cf.project8.service;

import gr.aueb.cf.project8.dto.ReportFaultDTO;
import gr.aueb.cf.project8.model.Fault;
import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import gr.aueb.cf.project8.repository.FaultRepository;
import gr.aueb.cf.project8.repository.UserRepository;


@Service
public class FaultService {

    @Autowired
    private FaultRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Fault saveFault(ReportFaultDTO dto, String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        Fault fault = new Fault();
        fault.setLocation(dto.getLocation());
        fault.setFaultType(dto.getFaultType());
        fault.setDescription(dto.getDescription());
        fault.setContactInfo(dto.getContactInfo());
        fault.setUser(user.get());

        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            try {
                fault.setPhoto(dto.getPhoto().getBytes());
                byte[] photoBytes = dto.getPhoto().getBytes();

                String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                fault.setPhotoBase64(photoBase64);
                dto.setPhotoBase64(photoBase64);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        repository.save(fault);
        return fault;
    }

    public List<Fault> findFaultsByUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        Long userId = user.get().getId();
        List<Fault> faults = repository.findFaultByUserId(userId);
        return Collections.unmodifiableList(faults);
    }

    public void deleteFaultById(Long id) {
        repository.deleteById(id);
    }

    public List<Fault> getAllFaults() {
        return repository.findAll();
    }


    public List<Fault> getFaultsByFilter(String filter) {
        List<Fault> faults;
//        switch (filter) {
//            case "type1":
//                return repository.findByFaultType("Electricity Fault");
//            case "type2":
//                return repository.findByFaultType("Damaged road infrastructure");
//            case "type3":
//                return repository.findByFaultType("Water grid failure");
//            case "type4":
//                return repository.findByFaultType("Garbage / Urban pollution");
//            default:
//                return repository.findAll();
//        }
        switch (filter) {
            case "type1":
                faults = repository.findByFaultType("Electricity Fault");
                break;
            case "type2":
                faults = repository.findByFaultType("Damaged road infrastructure");
                break;
            case "type3":
                faults = repository.findByFaultType("Water grid failure");
                break;
            case "type4":
                faults = repository.findByFaultType("Garbage / Urban pollution");
                break;
            default:
                faults = repository.findAll();
                break;
        }
        //System.out.println("Filter: " + filter + " | Found faults: " + faults.size());
        return faults;
    }





}


