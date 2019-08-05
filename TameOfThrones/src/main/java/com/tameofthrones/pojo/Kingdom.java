package com.tameofthrones.pojo;

public class Kingdom {

    private King king;
    private String name;
    private String emblem;

    public Kingdom() {
    }

    public Kingdom(King king, String name, String emblem) {
        this.king = king;
        this.name = name;
        this.emblem = emblem;
    }

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }

    /**
     * generated from eclipse
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emblem == null) ? 0 : emblem.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * generated from eclipse
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Kingdom other = (Kingdom) obj;
        if (emblem == null) {
            if (other.emblem != null)
                return false;
        } else if (!emblem.equals(other.emblem))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}
