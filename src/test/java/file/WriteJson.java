package file;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class WriteJson {
    public static void main(String[] args) {
        JSONObject employeeDetails1 = new JSONObject();
        employeeDetails1.put("firstName","test1");
        employeeDetails1.put("LastName","user1");
        employeeDetails1.put("website","codelean.com");

        JSONObject employeeObject1 = new JSONObject();
        employeeObject1.put("employee",employeeDetails1);

        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName","test2");
        employeeDetails2.put("LastName","user2");
        employeeDetails2.put("website","codelean.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee",employeeDetails2);

        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject1);
        employeeList.add(employeeObject2);

        try(FileWriter file = new FileWriter("employees.json")){
            file.write(employeeList.toJSONString());
            file.flush();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
