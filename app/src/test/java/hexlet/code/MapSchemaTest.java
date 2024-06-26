package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public final class MapSchemaTest {
    @Test
    public void testRequired() {
        MapSchema<String, String> schema = new Validator().map();
        HashMap<String, String>   person = new HashMap<>();

        assertThat(schema.isValid(person)).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);
        person.put("name1", "Sam");
        assertThat(schema.isValid(person)).isEqualTo(true);

        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
    }

    @Test
    public void testSizeof() {
        MapSchema<String, String> schema = new Validator().map();
        HashMap<String, String>   person = new HashMap<>();

        person.put("name1", "Sam");
        assertThat(schema.isValid(person)).isEqualTo(true);

        schema.sizeof(2);
        assertThat(schema.isValid(person)).isEqualTo(false);
        person.put("name2", "Dean");
        assertThat(schema.isValid(person)).isEqualTo(true);
    }

    @Test
    public void testShape() {
        Validator                       validator = new Validator();
        MapSchema<String, String>       schema    = new Validator().map();
        Map<String, BaseSchema<String>> schemas   = new HashMap<>();

        Map<String, String> person0 = new HashMap<>();
        person0.put("firstName", "S");
        person0.put("lastName", "W");
        assertThat(schema.isValid(person0)).isEqualTo(true);

        schemas.put("firstName", validator.string().required().minLength(2));
        schemas.put("lastName", validator.string().required().minLength(5));
        schema.shape(schemas);
        Map<String, String> person1 = new HashMap<>();
        person1.put("firstName", "Sam");
        person1.put("lastName", "Winchester");
        assertThat(schema.isValid(person1)).isEqualTo(true);

        Map<String, String> person2 = new HashMap<>();
        person2.put("firstName2", "Dean");
        person2.put("lastName2", null);
        assertThat(schema.isValid(person2)).isEqualTo(false);

        Map<String, String> person3 = new HashMap<>();
        person3.put("firstName", "D");
        person3.put("lastName", "Winchester");
        assertThat(schema.isValid(person3)).isEqualTo(false);

        Map<String, String> person4 = new HashMap<>();
        person4.put("firstName", "Bobby");
        person4.put("lastName", "S");
        assertThat(schema.isValid(person4)).isEqualTo(false);
    }
}
