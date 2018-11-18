package com.dj.cm.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Challenge implements Serializable {

    /**
     * Варианты доступа к челленджу
     */
    public enum AccessEnum {
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
    private AccessEnum access;

    public AccessEnum getAccess() {
        return access;
    }

    public void setAccess(AccessEnum access) {
        this.access = access;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;

    public Challenge(){

    }

    public Challenge(String name, String type, String description, int priority, int maxMembers, AccessEnum access, StatusEnum status, Date startDate, Date endDate) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.priority = priority;
        this.maxMembers = maxMembers;
        this.access = access;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Challenge{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", priority=").append(priority);
        sb.append(", maxMembers=").append(maxMembers);
        sb.append(", access=").append(access);
        sb.append(", status=").append(status);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append('}');
        return sb.toString();
    }
}
