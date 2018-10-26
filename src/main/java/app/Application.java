package app;

import app.datalog.DatalogController;
import app.index.IndexController;
import org.apache.log4j.BasicConfigurator;
import app.util.Filters;
import app.util.Path;

import java.util.Map;

import static spark.Spark.*;


public class Application {

    private static Map<String, String> env = System.getenv();


    // export PISSWORD=<password>
    // gets the keystore password set as an enviroment variable
    private static String getPassword() {
        return env.get("PISSWORD");
    }

    private static String getKeystorePath() {
        return env.get("KEYSTORE");
    }


    public static void main(String[] args) {

        BasicConfigurator.configure();

        port(4567);
        if (getPassword() != null && getKeystorePath() != null) {
            secure(getKeystorePath(), getPassword(), null, null);
        }

        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        before("*", Filters.addTrailingSlashes);
        before("*", Filters.handleLocaleChange);

        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.PARSE, DatalogController.parseDatalog);

        after("*", Filters.addGzipHeader);
    }
}
