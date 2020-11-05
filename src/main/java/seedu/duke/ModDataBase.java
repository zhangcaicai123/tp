package seedu.duke;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class ModDataBase {

    public static HashMap<String, Module> modules = new HashMap<>();

    /**
     * Convert module from Json object to local module object.
     *
     * @param module JSON object module.
     */
    public static void parseModule(JSONObject module) {

        Module mod = new Module();
        mod.moduleCode = (String) module.get("moduleCode");
        mod.title = (String) module.get("title");
        mod.description = (String) module.get("description");
        mod.moduleCredit = (String) module.get("moduleCredit");
        modules.put(mod.moduleCode, mod);

    }

    /**
     * Read module from storage and retrieve information from JSON.
     *
     * @throws IOException If unable to read data from storage.
     * @throws ParseException If unable to parse data from storage.
     */
    public static void getModFromFile() throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("data/AllModsInfo.json"));
        JSONArray moduleList = (JSONArray) obj;
        moduleList.forEach(module -> parseModule((JSONObject) module));

    }










}
