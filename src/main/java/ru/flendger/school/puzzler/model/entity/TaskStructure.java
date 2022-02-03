package ru.flendger.school.puzzler.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "task_structures")
public class TaskStructure extends BaseEntity{
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "taskStructure")
    private List<TaskColumn> taskColumns;
}
