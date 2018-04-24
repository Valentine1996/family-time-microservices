/**
 * Created by Andrii Gaidychuk on 24.09.2016.
 */

package com.valentyn.familytime.task.model.repository;

import com.valentyn.familytime.task.model.entity.Task;
import com.valentyn.familytime.task.model.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface for work with persistence layout
 *
 * @version 1.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t from Task t " +
            "WHERE t.performer.id = :performerId " +
            "AND t.parentTask IS NULL " +
            "AND t.status = :status")
    public List<Task> findHeadTasksByPerformerAndStatus(@Param("performerId") Long performerId,
                                                        @Param("status") TaskStatus status);

    @Query("SELECT t from Task t " +
            "WHERE t.creator.id = :creatorId " +
            "AND t.parentTask IS NULL " +
            "AND t.status = :status")
    public List<Task> findHeadTasksByCreatorAndStatus(@Param("creatorId") Long performerId,
                                                      @Param("status") TaskStatus status);
}
