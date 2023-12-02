package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository for the compensation
 *
 * @author Brett Lubberts
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    Compensation findByEmployee_EmployeeId(String employeeId);
}
