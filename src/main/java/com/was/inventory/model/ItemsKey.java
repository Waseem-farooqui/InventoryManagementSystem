package com.was.inventory.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemsKey implements Serializable {



    private String name;

    private String color;

    public ItemsKey() {
    }

    public ItemsKey(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * getters and setters
     **/

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemsKey)) return false;
        ItemsKey that = (ItemsKey) o;
        return Objects.equals(getName(), that.getName()) &&
                Objects.equals(getColor(), that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getColor());
    }
}
