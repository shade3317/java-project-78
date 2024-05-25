package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        restrictions.put("positive", n -> n == null || n > 0);
        return this;
    }

    public NumberSchema range(int lowerLimit, int upperLimit) {
        restrictions.put("range", n -> n == null || (lowerLimit <= n && n <= upperLimit));
        return this;
    }
}
