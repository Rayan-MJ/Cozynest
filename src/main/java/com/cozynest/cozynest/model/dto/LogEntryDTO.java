package com.cozynest.cozynest.model.dto;

import java.time.LocalDateTime;

public class LogEntryDTO {
    private String entityType;      
    private Long entityId;          
    private String action;          
    private String performedBy;     
    private LocalDateTime timestamp = LocalDateTime.now();
    private String details; 
    
    public LogEntryDTO(String entityType, Long entityId, String action, String performedBy, LocalDateTime timestamp,String details) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.action = action;
        this.performedBy = performedBy;
        this.timestamp = timestamp;
        this.details = details;
    }
    
    public LogEntryDTO() {
    }

    public String getEntityType() {
        return entityType;
    }
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
    public Long getEntityId() {
        return entityId;
    }
    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getPerformedBy() {
        return performedBy;
    }
    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
}
