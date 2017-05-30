package edu.agh.yait.persistence.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

//    @Column
//    private User creator;

    @Column
    private Integer score;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private IssueStatus status;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", locale = "pl-PL")
    private Date statusChangeDate;

    @OneToMany
    @JoinColumn(name = "issueId")
    private List<Comment> comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
