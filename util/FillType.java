package homework5.util;

public enum FillType {
    MANUAL(1, "Ввод вручную"),
    FILE(2, "Ввод из файла"),
    RANDOM(3, "Случайная генерация");

    private final int menuNumber;
    private final String description;

    FillType(int menuNumber, String description) {
        this.menuNumber = menuNumber;
        this.description = description;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public String getDescription() {
        return description;
    }

    public static FillType fromMenuNumber(int menuNumber) {
        for (FillType fillType : values()) {
            if (fillType.menuNumber == menuNumber) {
                return fillType;
            }
        }
        return null;
    }
}
