package com.valentyn.familytime.task.security.model.entity;

import com.google.common.base.MoreObjects;
import com.valentyn.familytime.task.model.entity.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Entity for persist requests about lost access.
 *
 * @version 1.0
 */
@Entity
@Table(
        name = "recovery_access",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "user_id"
                        }
                )
        }
)
public class RecoveryAccess {

    /// *** Properties  *** ///
    /**
     * Unique id of record.
     */
    @Id
    @GeneratedValue
    @Column
    protected Long id;

    /**
     * User for recovery access.
     */
    @NotNull
    @Valid
    @OneToOne
    @JoinColumn(name = "user_id")
    protected User user;

    /**
     * Unique hash as one-time link for reset password.
     */
    @NotNull
    @NotEmpty
    @Column(length = 256)
    protected String hash;

    /**
     * Time of expiration.
     */
    @NotNull
    @Future
    @Column(name = "expired_at")
    protected OffsetDateTime expiredAt;

    /// *** Methods     *** ///

    /**
     * Default constructor.
     */
    public RecoveryAccess() {
        //- Initialization -//
    }

    /**
     * Create request for recovery access.
     *
     * @param user      User for recovery access.
     * @param hash      One-time hash for recovery access.
     * @param expiredAt Expiry time.
     */
    public RecoveryAccess(
            User user,
            String hash,
            OffsetDateTime expiredAt
    ) {
        this.user = user;
        this.hash = hash;
        this.expiredAt = expiredAt;
    }


    //- SECTION :: GET -//

    /**
     * Getter for id.
     *
     * @return Long
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for id.
     *
     * @param id Id of hash.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for user.
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for user.
     *
     * @param user User for recovery access.
     */
    public void setUser(User user) {
        this.user = user;
    }

    //- SECCTION :: SET -//

    /**
     * Getter for unique hash for recovery access.
     *
     * @return String
     */
    public String getHash() {
        return hash;
    }

    /**
     * Setter for unique hash.
     *
     * @param hash Unique hash for recovery access.
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Getter for expiry time.
     *
     * @return OffsetDateTime
     */
    public OffsetDateTime getExpiredAt() {
        return expiredAt;
    }

    /**
     * Set expiry time.
     *
     * @param expiredAt Expiry time for using this hash.
     */
    public void setExpiredAt(OffsetDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, user, hash, expiredAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final RecoveryAccess other = (RecoveryAccess) obj;
        return Objects.equals(this.id, other.id)
                && Objects.equals(this.user, other.user)
                && Objects.equals(this.hash, other.hash)
                && Objects.equals(this.expiredAt, other.expiredAt);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("user", user)
                .add("hash", hash)
                .add("expiredAt", expiredAt)
                .toString();
    }
}
