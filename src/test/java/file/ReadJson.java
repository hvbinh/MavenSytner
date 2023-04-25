package file;

import com.mongodb.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ReadJson {
    public static void main(String[] args) {

        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("employees.json")){
            Object object = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) object;
            System.out.println(employeeList);

            employeeList.forEach(emp -> parseEmployeeObject((JSONObject) emp));

    }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void parseEmployeeObject(JSONObject employee)
    {
        JSONObject employeeObject = (JSONObject) employee.get("employee");

        String firstName = (String) employeeObject.get("firstName");
        System.out.println("firstName: "+firstName);

        String lastName = (String) employeeObject.get("lastName");
        System.out.println("lastName: "+lastName);

        String website = (String) employeeObject.get("website");
        System.out.println("website: "+website);
    }
}
