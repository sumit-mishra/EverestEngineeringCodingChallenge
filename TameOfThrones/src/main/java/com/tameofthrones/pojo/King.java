package com.tameofthrones.pojo;

public class King {

    private String kingName;

    public King(final String kingName) {
        this.kingName = kingName;
    }

    public String getKingName() {
        return kingName;
    }

    public void setKingName(String kingName) {
        this.kingName = kingName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((kingName == null) ? 0 : kingName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        King other = (King) obj;
        if (kingName == null) {
            if (other.kingName != null)
                return false;
        } else if (!kingName.equals(other.kingName))
            return false;
        return true;
    }

}
