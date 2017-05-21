package edu.agh.yait.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class IssueDTO {

    @NotEmpty
    @Length(max = 140)
    private String title;

    @NotEmpty
    @Length(max = 140)
    private String description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
