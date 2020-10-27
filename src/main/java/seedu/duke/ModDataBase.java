package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ModDataBase {

    public static HashMap<String, Module> modules = new HashMap<String, Module>();

    public static void parseModule(JSONObject module) {

        Module mod = new Module();
        mod.moduleCode = (String) module.get("moduleCode");
        mod.title = (String) module.get("title");
        mod.description = (String) module.get("description");
        mod.moduleCredit = (String) module.get("moduleCredit");
        modules.put(mod.moduleCode, mod);

    }

    public static void getModFromFile() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/java/data/allModInfo.json"));
        JSONArray moduleList = (JSONArray) obj;
        moduleList.forEach(module->parseModule((JSONObject) module));

    }




}
