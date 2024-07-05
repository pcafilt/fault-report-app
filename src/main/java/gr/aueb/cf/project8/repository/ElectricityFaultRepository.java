package gr.aueb.cf.project8.repository;

import gr.aueb.cf.project8.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import gr.aueb.cf.project8.model.ElectricityFault;

import java.util.List;

public interface ElectricityFaultRepository extends JpaRepository<ElectricityFault, Long> {
List<ElectricityFault> findByUser(User user);
}
