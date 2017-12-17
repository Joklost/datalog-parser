package app;

import app.datalog.DatalogController;
import app.index.IndexController;
import org.apache.log4j.BasicConfigurator;
import app.util.Filters;
import app.util.Path;

import static spark.Spark.*;


public class Application {

    private static boolean devel = true;

    public static void main(String[] args) {

        BasicConfigurator.configure();

        if (devel) {
            String projectDir = System.getProperty("user.dir");
            String staticDir = "/src/main/resources/public";
            staticFiles.externalLocation(projectDir + staticDir);
        } else {
            staticFiles.location("/public");
            staticFiles.expireTime(600L);
        }

        before("*", Filters.addTrailingSlashes);
        before("*", Filters.handleLocaleChange);

        get(Path.Web.INDEX, IndexController.serveIndexPage);
        get(Path.Web.PARSE, DatalogController.parseDatalog);

        after("*", Filters.addGzipHeader);
    }
}

/*

edge('A', 'B').
edge('B', 'C').
edge('A', 'E').
edge('C', 'D').
edge('D', 'E').
edge('F', 'G').
edge('E', 'G').


node('A').
node('B').
node('C').
node('D').
node('E').
node('F').
node('G').

path(?x, ?y) :- edge(?x, ?y).
path(?x, ?y) :- edge(?x, ?z),
                path(?z, ?y).

?- path(?x, ?y).


 */
