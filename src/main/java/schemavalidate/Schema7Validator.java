package schemavalidate;

import input.ReaderUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Schema7Validator {
    private String PATH = System.getProperty("user.dir")+"/schema/testP3.json";
    private String PATH1 = System.getProperty("user.dir")+"/schema/p.json";

    public static void main(String[] args){
        Logger loggerMain = LogManager.getLogger("MAIN");
        Schema7Validator validator = new Schema7Validator();
        InputStream stream = null;

        String schemaStr = ReaderUtil.ReadFileToString(validator.PATH1);
        String jsonStr = ReaderUtil.ReadFileToString(validator.PATH);
        loggerMain.info(schemaStr);
        loggerMain.info(jsonStr);
        JSONObject object = new JSONObject(schemaStr);
        SchemaLoader loader = SchemaLoader.builder().schemaJson(schemaStr).draftV6Support().build();
//        Schema schema = SchemaLoader.load(object);
        Schema schema =loader.load().build();
        JSONObject in = new JSONObject(jsonStr);

        try {
            schema.validate(in);
        }catch (ValidationException e){
            loggerMain.error(e.getMessage());
        }
    }
}
