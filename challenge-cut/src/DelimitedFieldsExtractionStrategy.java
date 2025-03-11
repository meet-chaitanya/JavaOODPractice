import java.util.ArrayList;
import java.util.List;

public class DelimitedFieldsExtractionStrategy implements FieldsExtractionStrategy{
    String delimiter;
    List<Integer> fieldNumbers;

    DelimitedFieldsExtractionStrategy(String delimiter, List<Integer> fieldNumbers) {
        this.delimiter = delimiter;
        this.fieldNumbers = fieldNumbers;
    }

    @Override
    public void extractFields(String line) {
        String[] fields = line.split(delimiter);
        List<String> outputFields = new ArrayList<>();
        for (int fieldNumber: fieldNumbers) {
            if (fieldNumber <= fields.length) {
                outputFields.add(fields[fieldNumber - 1]);
            }
        }
        System.out.println(String.join(delimiter, outputFields));
    }
}
