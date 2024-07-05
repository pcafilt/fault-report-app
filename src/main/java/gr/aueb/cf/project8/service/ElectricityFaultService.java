package gr.aueb.cf.project8.service;

import gr.aueb.cf.project8.dto.ReportElectricityFaultDTO;
import gr.aueb.cf.project8.model.ElectricityFault;
import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.repository.ElectricityFaultRepository;
import gr.aueb.cf.project8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@Service
public class ElectricityFaultService {

    @Autowired
    private ElectricityFaultRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ElectricityFault saveFault(ReportElectricityFaultDTO dto,String username) {

        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        ElectricityFault fault = new ElectricityFault();
        fault.setLocation(dto.getLocation());
        fault.setFaultType(dto.getFaultType());
        fault.setDescription(dto.getDescription());
        fault.setObservationTime(dto.getObservationTime());
        fault.setContactInfo(dto.getContactInfo());
        fault.setUser(user);

        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            try {
                fault.setPhoto(dto.getPhoto().getBytes());
                byte[] photoBytes = dto.getPhoto().getBytes();

                String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                fault.setPhotoBase64(photoBase64); // Set the base64 string in the entity
                dto.setPhotoBase64(photoBase64); // Ensure the base64 string is also set in the DTO

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error
            }
        }

        repository.save(fault);
        return fault;
    }
}
