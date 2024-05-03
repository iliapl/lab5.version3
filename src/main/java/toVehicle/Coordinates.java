package toVehicle;

public class Coordinates {
    private long x;
    private float y;

    public Coordinates(long x, float y) {
        this.x = x;
        if (y != 0 && y <= 597) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Недопустимое значение y");
        }
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public long getX() {
        return x;
    }
}
