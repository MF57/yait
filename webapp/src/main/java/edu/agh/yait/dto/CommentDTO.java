package edu.agh.yait.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class CommentDTO {

    @NotEmpty
    @Length(max = 140)
    private String text;

    public String getText() {
        return text;
    }
}
