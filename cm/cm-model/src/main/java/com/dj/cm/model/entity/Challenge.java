package com.dj.cm.model.entity;

import lombok.Data;

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
@Data
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

    @Column
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    private Date startDate;

    private Date endDate;

    private String pictureFilename;
}
