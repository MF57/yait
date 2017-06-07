package edu.agh.yait.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class VoteDTO {

    @NotNull
    @Min(value = 1)
    private Integer points;

    public Integer getPoints() {
        return points;
    }
}
