package com.sirma.employees.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectBean {
	
    private int projectId;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    
    public ProjectBean() {
    }

    public ProjectBean(int projectId, LocalDateTime from, LocalDateTime to) {
        this.projectId = projectId;
        this.fromDate = from;
        this.toDate = to;
    }
    
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime from) {
        this.fromDate = from;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime to) {
        this.toDate = to;
    }

}
