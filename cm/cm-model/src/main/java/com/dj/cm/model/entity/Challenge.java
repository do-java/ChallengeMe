package com.dj.cm.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Challenge implements Serializable {

    /**
     * Варианты доступа к челленджу
     */
    public enum AcessEnum {
        PRIVATE,
        PUBLIC,
        PROTECTED;      //друзья друзей? или пользователи определенной группы
    }

    /**
     * Варианты статуса челленджа
     */
    public enum StatusEnum {
        ACTIVE,         //действующий, без текущего дедлайна
        BLOCKED,        // заблокированный администраторами/модираторами
        DEADLINE,       // до завершения осталось менее 15% от общей продолжительности (или, напр, 1 день)
        FINISHED,       // завершенный, не в архиве
        ARCHIVATED;     // в архиве
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String description;

    private int priority;
    private int maxMembers;

    @Column
    @Enumerated(EnumType.STRING)
    private AcessEnum acess;

    public AcessEnum getAcess() {
        return acess;
    }

    public void setAcess(AcessEnum acess) {
        this.acess = acess;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Date startDate;
    private Date endDate;

    public Challenge(){

    }

    public Challenge(String name, String type, String description, int priority, int maxMembers, AcessEnum acess, StatusEnum status, Date startDate, Date endDate) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.priority = priority;
        this.maxMembers = maxMembers;
        this.acess = acess;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", maxMembers=" + maxMembers +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
