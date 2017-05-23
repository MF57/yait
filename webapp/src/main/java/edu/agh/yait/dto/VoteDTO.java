package edu.agh.yait.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class VoteDTO {

    @NotNull
    @Min(value = 1)
    private Integer points;

    public Integer getPoints() {
        return points;
    }
}
