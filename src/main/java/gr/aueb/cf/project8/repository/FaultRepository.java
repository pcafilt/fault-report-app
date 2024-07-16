package gr.aueb.cf.project8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import gr.aueb.cf.project8.model.Fault;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface FaultRepository extends JpaRepository<Fault, Long> {

    List<Fault> findFaultByUserId( Long userId);
    List<Fault> findByFaultType(String faultType);

}
