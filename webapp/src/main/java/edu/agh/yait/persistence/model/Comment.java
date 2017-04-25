package edu.agh.yait.persistence.model;

import javax.persistence.*;
import java.util.Date;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column
    private User user;

    @Column
    private String comment;
}
