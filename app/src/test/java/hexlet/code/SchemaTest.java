package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public final class SchemaTest {
    @Test
    public void testStringSchema() {
        StringSchema schema = new Validator().string();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid("")).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid("War")).isEqualTo(true);
        assertThat(schema.isValid("So what am I fighting for")).isEqualTo(true);

        schema.minLength(25);
        assertThat(schema.isValid("Everything back and more")).isEqualTo(false);
        assertThat(schema.isValid("And I'm not gonna let this go")).isEqualTo(true);

        schema.contains("war");
        assertThat(schema.isValid("I'm ready to settle the score")).isEqualTo(false);
        assertThat(schema.isValid("Get ready cause this is war.")).isEqualTo(true);
    }

    @Test
    public void testNumberSchema() {
        NumberSchema schema = new Validator().number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(4)).isEqualTo(true);
        assertThat(schema.isValid(-6)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);

        schema.positive();
        assertThat(schema.isValid(8)).isEqualTo(true);
        assertThat(schema.isValid(-10)).isEqualTo(false);

        schema.range(10, 15);
        assertThat(schema.isValid(12)).isEqualTo(true);
        assertThat(schema.isValid(20)).isEqualTo(false);
    }

    @Test
    public void testMapSchema() {
        MapSchema<String, String> schema = new Validator().map();
        HashMap<String, String>   person = new HashMap<>();

        assertThat(schema.isValid(person)).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
        person.put("name1", "Sam");
        assertThat(schema.isValid(person)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);

        schema.sizeof(2);
        assertThat(schema.isValid(person)).isEqualTo(false);
        person.put("name2", "Dean");
        assertThat(schema.isValid(person)).isEqualTo(true);
    }

    @Test
    public void testMapShapeSchema() {
        Validator                       validator = new Validator();
        MapSchema<String, String>       schema    = new Validator().map();
        Map<String, BaseSchema<String>> schemas   = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);
        Map<String, String> person1 = new HashMap<>();
        person1.put("firstName", "Sam");
        person1.put("lastName", "Winchester");
        assertThat(schema.isValid(person1)).isEqualTo(true);

        Map<String, String> person2 = new HashMap<>();
        person2.put("firstName", "Dean");
        person2.put("lastName", null);
        assertThat(schema.isValid(person2)).isEqualTo(false);

        Map<String, String> person3 = new HashMap<>();
        person3.put("firstName", "Bobby");
        person3.put("lastName", "S");
        assertThat(schema.isValid(person3)).isEqualTo(false);
    }
}
