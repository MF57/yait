package edu.agh.yait.persistence.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Date creationDate;

    @Column
    private User creator;

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
}
