package file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class readUserList {
    public static void main(String[] args) {
        JSONParser jsonParser = new JSONParser();

        try(FileReader fileReader = new FileReader("userList.json"))
        {
            Object object = jsonParser.parse(fileReader);
            System.out.println(object.toString());
            JSONObject user = (JSONObject) object;
            JSONArray array = (JSONArray) user.get("data");
            System.out.println(array.get(0));
            user = (JSONObject) array.get(0);
            for(Iterator iterator = user.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                System.out.println(user.get(key));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
//    public void returnTotal(Object object)
//    {
//        System.out.println(object.key);
//    }
}
