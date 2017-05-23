package edu.agh.yait.persistence.model;

public enum  IssueStatus {
    Opened, Closed;

    public IssueStatus getAnotherStatus() {
        if (this == Opened) {
            return Closed;
        } else {
            return Opened;
        }
    }
}
