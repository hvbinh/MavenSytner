package common;

import com.mongodb.client.*;
import org.bson.Document;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class test {
    @BeforeClass
    public void init()
    {
        //String url = "mongodb://thinhnguyenfreec@gmail.com:12345678@localhost";
        MongoClient mongoClient = MongoClients.create();
        MongoIterable<String> dbNames = mongoClient.listDatabaseNames();
        for(String db : dbNames)
        {
            System.out.println(db);
        }
        MongoDatabase db = mongoClient.getDatabase("adsist_db");
        MongoCollection<Document> user = db.getCollection("users");

        FindIterable<Document> result = user.find();
        for(Document document : result)
        {
            System.out.println(document);
        }

    }
    @Test
    public void TC_01()
    {

    }
}
