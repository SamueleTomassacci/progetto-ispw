package it.uniroma2.dicii.ispw.utils.bean;

public enum Role {
    GIOCATORE(1),
    GESTORE(2),
    PROPRIETARIO(3);

    private final int id;

    private Role(int id) {
        this.id = id;
    }

    public static Role fromInt(int id) {
        for (Role type : values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}