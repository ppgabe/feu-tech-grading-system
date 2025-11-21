import java.util.List;

public enum StudentType {
    UNDERGRADUATE("Undergraduate"),
    GRADUATE("Graduate");

    private final String formattedName;

    private StudentType(String s) {
        this.formattedName = s;
    }

    @Override
    public String toString() {
        return this.formattedName;
    }
}
