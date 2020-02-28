package json.serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.apache.avro.Schema;
import org.apache.avro.specific.SpecificRecord;

@Data
@AllArgsConstructor
@ToString
public class CustomObjectPart2  {

    private String firstname;

    private String lastname;

    private Integer age;


}
