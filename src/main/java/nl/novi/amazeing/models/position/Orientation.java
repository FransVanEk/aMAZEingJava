package nl.novi.amazeing.models.position;

public enum Orientation {
    FacingUp,
    FacingRight,
    FacingDown,
    FacingLeft;

    public Orientation turnLeft() {
        switch (this) {
            case FacingRight -> {
                return Orientation.FacingUp;
            }
            case FacingUp -> {
                return Orientation.FacingLeft;
            }
            case FacingLeft -> {
                return Orientation.FacingDown;
            }
            case FacingDown -> {
                return Orientation.FacingRight;
            }
        }
        return this;
    }

    public Orientation turnRight() {
        switch (this) {
            case FacingRight -> {
                return Orientation.FacingDown;
            }
            case FacingUp -> {
                return Orientation.FacingRight;
            }
            case FacingLeft -> {
                return Orientation.FacingUp;
            }
            case FacingDown -> {
                return Orientation.FacingLeft;
            }
        }
        return this;
    }
}
