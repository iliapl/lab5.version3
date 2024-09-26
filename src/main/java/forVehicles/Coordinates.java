package forVehicles;

import lombok.Getter;

@Getter
public class Coordinates {
    private final long x;
    private final float y;
    public Coordinates(long x, float y) {
        this.x = x;
        if (y != 0 && y <= 597) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Недопустимое значение y");
        }
    }
    @Override
    public String toString() {
        return String.format("x = %d, y = %.2f", x, y);
    }
}
