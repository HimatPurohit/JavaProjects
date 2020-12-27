package com.examples.LiveLocks;

public class SharedResources {
    private Worker owner;

    public SharedResources(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    public void setOwner(Worker owner) {
        this.owner = owner;
    }
}
