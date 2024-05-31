package hexlet.code;

import hexlet.code.schemas.NumberSchema;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {
    @Test
    public void testRequired() {
        NumberSchema schema = new Validator().number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(true);
        assertThat(schema.isValid(-6)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
    }

    @Test
    public void testPositive() {
        NumberSchema schema = new Validator().number();

        assertThat(schema.isValid(8)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(true);

        schema.positive();
        assertThat(schema.isValid(8)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(false);
    }

    @Test
    public void testRange() {
        NumberSchema schema = new Validator().number();

        assertThat(schema.isValid(12)).isEqualTo(true);
        assertThat(schema.isValid(20)).isEqualTo(true);

        schema.range(10, 15);
        assertThat(schema.isValid(12)).isEqualTo(true);
        assertThat(schema.isValid(20)).isEqualTo(false);
    }
}
