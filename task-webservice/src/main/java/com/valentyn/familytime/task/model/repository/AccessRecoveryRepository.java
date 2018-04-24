package com.valentyn.familytime.task.model.repository;

import com.valentyn.familytime.task.security.model.entity.RecoveryAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface AccessRecoveryRepository extends JpaRepository<RecoveryAccess, Long> {
    /**
     * Find by hash.
     *
     * @param hash One time hash.
     * @return RecoveryAccess.
     */
    RecoveryAccess findByHash(String hash);

    /**
     * Delete by hash.
     *
     * @param hash One time hash.
     * @return RecoveryAccess.
     */
    RecoveryAccess deleteByHash(String hash);

    /**
     * Get valid hash.
     *
     * @param hash One time hash.
     * @return RecoveryAccess Valid access token.
     */
    @Query(value = "SELECT ra " +
            "FROM RecoveryAccess ra " +
            "WHERE ra.hash = :hash " +
            "AND CURRENT_TIMESTAMP < ra.expiredAt")
    RecoveryAccess checkAccess(@Param("hash") String hash);
}
