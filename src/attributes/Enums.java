package attributes;

public class Enums {
    public enum TypKonta {
        ADMIN,
        URSER
    }

    public static TypKonta typKonta;

    public void setTypKonta(TypKonta typKonta) {
        Enums.typKonta = typKonta;
    }

    public TypKonta getTypKonta() {
        return typKonta;
    }
}
