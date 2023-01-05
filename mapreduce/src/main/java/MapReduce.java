import com.sun.javafx.logging.JFRInputEvent;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapReduce {


    public static List<KeyValue> mapF(String filename, String contents){
        List<KeyValue> res = new ArrayList<>();
        String[] words = contents.split("\\W+");
        for(String word : words){
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(word);
            keyValue.setValue("1");
            res.add(keyValue);
        }
        return res;
    }

    public static String reduceF(String key, String[] values){
        return String.valueOf(values.length);
    }

    public static void main(String[] args) {
        List<KeyValue> intermediate = new ArrayList<>();
        String filename = "pg-being_ernest.txt";
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
            String contents = IOUtils.toString(resourceAsStream);
            List<KeyValue> keyValues = mapF(filename, contents);
            intermediate.addAll(keyValues);
//            完事 排序
            intermediate.sort((a, b) -> a.getKey().compareTo(b.getKey()));
//            System.out.println(intermediate);

            for (int i = 0; i < intermediate.size(); i++){
                int j = i + 1;
                while(j < intermediate.size() && intermediate.get(j).getKey().equals(intermediate.get(i).getKey())){
                    j++;
                }
                List<String> values = new ArrayList<>();
                for(int k = i; k < j; k++){
                    values.add(intermediate.get(k).getValue());
                }
                String s = reduceF(intermediate.get(i).getKey(), values.toArray(new String[0]));
                System.out.format("%s: %s\n",intermediate.get(i).getKey(), s);
                i = j;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
