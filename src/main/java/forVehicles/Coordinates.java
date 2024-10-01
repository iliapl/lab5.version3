package forVehicles;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Coordinates {
    private final long x;
    private final float y;

    @Override
    public String toString() {
        return String.format("x = %d, y = %.2f", x, y);
    }
}
