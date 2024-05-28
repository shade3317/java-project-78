package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        addRestrictions("positive", n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int lowerLimit, int upperLimit) {
        addRestrictions("range", n -> n == null || (lowerLimit <= n && n <= upperLimit));
        return this;
    }
}
