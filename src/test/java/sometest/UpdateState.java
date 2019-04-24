package sometest;

import org.apache.commons.lang.Validate;

public enum UpdateState {
    UPDATEABLE(()-> Validate.isTrue(true)), READONLY(()->Validate.isTrue(false));
    private Runnable action;
    UpdateState(Runnable action) {
        this.action=action;
    }
    public <T> T set(T value) {
        action.run();
        return value;
    }
}