package com.lyaev.h2writer.model;

import javax.persistence.*;

/**
 * Message's entity
 */
@Entity
@Table(name="MESSAGES")
public class MessageEntity {
    public MessageEntity(String message) {
        this.message = message;
    }

    public MessageEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    public Long id;

    @Column(name="message", nullable=false)
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "id=" + id + ", message=" + message;
    }
}
