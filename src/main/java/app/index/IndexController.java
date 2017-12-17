package app.index;

import spark.Request;
import spark.Response;
import spark.Route;
import app.util.Path;
import app.util.ViewUtil;

import java.util.HashMap;
import java.util.Map;

public class IndexController {

    public static Route serveIndexPage = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        return ViewUtil.render(request, model, Path.Template.INDEX);
    };
}
