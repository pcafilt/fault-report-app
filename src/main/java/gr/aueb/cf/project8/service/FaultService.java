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
        fault.setObservationTime(dto.getObservationTime());
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

}


