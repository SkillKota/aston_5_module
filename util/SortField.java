package homework5.util;

public enum SortField {
    ID(1, "id"),
    NAME(2, "name"),
    EMAIL(3, "email"),
    PASSWORD(4, "password");

    private final int menuNumber;
    private final String description;

    SortField(int menuNumber, String description) {
        this.menuNumber = menuNumber;
        this.description = description;
    }

    public int getMenuNumber() {
        return menuNumber;
    }

    public String getDescription() {
        return description;
    }

    public static SortField fromMenuNumber(int menuNumber) {
        for (SortField sortField : values()) {
            if (sortField.menuNumber == menuNumber) {
                return sortField;
            }
        }
        return null;
    }
}
