package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> restrictions = new HashMap<>();

    /**
     * Checks that all restrictions were respected.
     * @param object to be checked
     * @return true if all restrictions were respected, otherwise false
     */
    public boolean isValid(T object) {
        return restrictions.values().stream().allMatch(r -> r.test(object));
    }

    /**
     * Add nonNull restrictions.
     * @return this object type
     */
    public BaseSchema<T> required() {
        addRestrictions("required", Objects::nonNull);
        return this;
    }

    protected final void addRestrictions(String nameRestrictions, Predicate<T> condition) {
        restrictions.put(nameRestrictions, condition);
    }
}
