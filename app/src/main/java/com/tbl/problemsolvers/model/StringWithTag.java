package com.tbl.problemsolvers.model;

/*class for spiner data*/
public class StringWithTag {
    public String name;
    public Object id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }


    public StringWithTag(String name, Object tag) {
        this.name = name;
        this.id = tag;
    }

    @Override
    public String toString() {
        return name;
    }
}