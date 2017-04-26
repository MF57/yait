package edu.agh.yait.persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Date creationDate;

//    @Column
//    private User creator;

    @Column
    private Integer points;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private IssueStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date statusChangeDate;

    @OneToMany
    List<Comment> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

//    public User getCreator() {
//        return creator;
//    }
//
//    public void setCreator(User creator) {
//        this.creator = creator;
//    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Date getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(Date statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
