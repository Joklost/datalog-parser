package app.datalog;

import app.util.ViewUtil;
import org.deri.iris.EvaluationException;
import org.deri.iris.compiler.ParserException;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.servlet.MultipartConfigElement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static app.util.RequestUtil.clientAcceptsJson;
import static app.util.RequestUtil.getQueryDatalogProgram;
import static app.util.JsonUtil.dataToJson;

public class DatalogController {
    private static DatalogParser parser = new DatalogParser();

    public static Route parseDatalog = (Request request, Response response) -> {
        String datalogProgram = getQueryDatalogProgram(request);
        if (datalogProgram == null) {
            return ViewUtil.notAcceptable.handle(request, response);
        }

        List<String> result = parser.parse(datalogProgram);
        return dataToJson(result);
    };
}
