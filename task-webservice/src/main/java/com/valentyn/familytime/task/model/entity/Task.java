/**
 * Created by Andrii Gaidychuk on 18.09.2016.
 */

package com.valentyn.familytime.task.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.valentyn.familytime.task.model.serializer.JsonDataTimeSerializer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class for reflect table parentTask from persistence layout
 *
 * @version 1.0
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "task")
public class Task implements Serializable {

    /// *** Properties  *** ///

    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "task_type_id")
    protected TaskType taskType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "complexity_id")
    protected Complexity complexity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creator_id")
    protected User creator;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "performer_id")
    protected User performer;

    @ManyToOne
    @JoinColumn(name = "bonus_id")
    protected Bonus bonus;

    @NotNull
    @Column(name = "has_subtasks")
    @Value("false")
    protected Boolean hasSubtasks;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    protected Task parentTask;

    @Column(name = "step")
    protected Integer step;

    @NotBlank
    @Column(name = "description")
    protected String description;

    @NotNull
    @Column(name = "prize")
    protected Integer prize;

    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    protected TaskStatus status;

    @NotNull
    @JsonSerialize(using = JsonDataTimeSerializer.class)
    @Column(name = "close_to")
    protected LocalDateTime closeTo;

    @JsonSerialize(using = JsonDataTimeSerializer.class)
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public Task() {
    }

    /**
     * Constructor.
     *
     * @param taskType    Task's type
     * @param complexity  Task's complexity
     * @param creator     Task's creator
     * @param performer   Task's performer
     * @param bonus       Task's bonus
     * @param step        Task's step
     * @param description Task's description
     * @param prize       Task's prize
     * @param closeTo     Task's close date
     */
    public Task(TaskType taskType, Complexity complexity, User creator, User performer,
                Bonus bonus, Integer step, String description,
                Integer prize, LocalDateTime closeTo) {

        this.taskType = taskType;
        this.complexity = complexity;
        this.creator = creator;
        this.performer = performer;
        this.bonus = bonus;
        this.step = step;
        this.description = description;
        this.prize = prize;
        this.closeTo = closeTo;
        this.hasSubtasks = false;
        this.status = TaskStatus.OPEN;
    }

    /**
     * Constructor.
     *
     * @param taskType    Task's type
     * @param complexity  Task's complexity
     * @param creator     Task's creator
     * @param performer   Task's performer
     * @param bonus       Task's bonus
     * @param step        Task's step
     * @param description Task's description
     * @param prize       Task's prize
     * @param closeTo     Task's close date
     * @param parentTask  Task's parent
     */
    public Task(TaskType taskType, Complexity complexity, User creator, User performer,
                Bonus bonus, Integer step, String description, Integer prize, LocalDateTime closeTo,
                Task parentTask) {

        this.taskType = taskType;
        this.complexity = complexity;
        this.creator = creator;
        this.performer = performer;
        this.bonus = bonus;
        this.step = step;
        this.description = description;
        this.prize = prize;
        this.closeTo = closeTo;
        this.hasSubtasks = true;
        this.parentTask = parentTask;
        this.status = TaskStatus.OPEN;
    }

    @PrePersist
    public void perStore() {
        this.createdAt = LocalDateTime.now();
    }

    //- SECTION :: GET -//

    /**
     * Get ID of the taks.
     *
     * @return Long ID of the Task
     */
    public Long getId() {
        return id;
    }

    /**
     * Set ID for task.
     *
     * @param id ID of the task
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get Type of the taks.
     *
     * @return TaksType of the Task
     */
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * Set Task Type for task.
     *
     * @param taskType task type of the task
     */
    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Get Complexity of the task.
     *
     * @return Complexity of the Task
     */
    public Complexity getComplexity() {
        return complexity;
    }

    /**
     * Set Complexity for task.
     *
     * @param complexity complexity of the task
     */
    public void setComplexity(Complexity complexity) {
        this.complexity = complexity;
    }

    /**
     * Get Creator of the task.
     *
     * @return User that created Task
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Set Creator for task.
     *
     * @param creator User creator of the task
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * Get Perfomer of the task.
     *
     * @return User that perform Task
     */
    public User getPerformer() {
        return performer;
    }

    /**
     * Set Performer for task.
     *
     * @param performer User performer of the task
     */
    public void setPerformer(User performer) {
        this.performer = performer;
    }

    /**
     * Get Bonus of the task.
     *
     * @return Bonus of the Task
     */
    public Bonus getBonus() {
        return bonus;
    }

    /**
     * Set Bonus for task.
     *
     * @param bonus Bonus of the task
     */
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    /**
     * Get Flag if task has sub tasks.
     *
     * @return Boolean 'true' if task has sub tasks
     */
    public Boolean getHasSubtasks() {
        return hasSubtasks;
    }

    /**
     * Set Flag if task has sub tasks.
     *
     * @param hasSubtasks Boolean flag of the task
     */
    public void setHasSubtasks(Boolean hasSubtasks) {
        this.hasSubtasks = hasSubtasks;
    }

    //- SECTION :: SET -//

    /**
     * Get Parent task of the task.
     *
     * @return Task which is parent for the current task
     */
    public Task getParentTask() {
        return parentTask;
    }

    /**
     * Set Parent task.
     *
     * @param parentTask parent Task
     */
    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    /**
     * Get Step of the task.
     *
     * @return Integer value step of the current task
     */
    public Integer getStep() {
        return step;
    }

    /**
     * Set Step for task.
     *
     * @param step Step of the task
     */
    public void setStep(Integer step) {
        this.step = step;
    }

    /**
     * Get Description of the task.
     *
     * @return String description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set Description for task.
     *
     * @param description of the task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get Prize of the task.
     *
     * @return Integer prize of the task
     */
    public Integer getPrize() {
        return prize;
    }

    /**
     * Set Prize for task.
     *
     * @param prize of the task
     */
    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    /**
     * Get Close date of the task.
     *
     * @return LocalDateTime close date of the task
     */
    public LocalDateTime getCloseTo() {
        return closeTo;
    }

    /**
     * Set Close date for task.
     *
     * @param closeTo of the task
     */
    public void setCloseTo(LocalDateTime closeTo) {
        this.closeTo = closeTo;
    }

    /**
     * Get Create date of the task.
     *
     * @return LocalDateTime create date of the task
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Set Create date for task.
     *
     * @param createdAt of the task
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Get task's status.
     *
     * @return task's status
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * set task's status.
     *
     * @param status task's status
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
