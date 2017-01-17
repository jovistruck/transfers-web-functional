package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by jovianodias on 05/10/2016.
 */
public class DataProvider {
    private JsonObject manuscriptsFileObject = null;

    public DataProvider() {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final FileReader fileReader;
        Gson gson = new GsonBuilder().create();

        try {
            String manuscriptsDataFileName = "data/manuscripts.json";
            fileReader = new FileReader(classLoader.getResource(manuscriptsDataFileName).getFile());
            manuscriptsFileObject = gson.fromJson(fileReader, JsonObject.class).getAsJsonObject("data").getAsJsonObject("manuscripts");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public String getManuscriptInfo(String doiNumber, String contentType){

        return manuscriptsFileObject.getAsJsonObject(doiNumber).get(contentType).toString().replaceAll("^\"|\"$", "");
    }

    public List<String> getDoiList(){

        List<String> doiList= new ArrayList<String>();

        Set<Map.Entry<String, JsonElement>> entries = manuscriptsFileObject.entrySet();//will return members of your object
        for (Map.Entry<String, JsonElement> entry: entries) {
            doiList.add(entry.getKey());
        }

        return doiList;
    }

    public static void main(String args[]) {

        DataProvider dataProvider=new DataProvider();
       // System.out.println(dataProvider.getManuscriptInfo("10.1186/s12864-015-2313-7","body"));
        dataProvider.getDoiList();

    }

}
