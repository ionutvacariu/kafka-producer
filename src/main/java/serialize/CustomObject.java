package serialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CustomObject {

    private String firstname;

    private String lastname;

    private Integer age;

}
