package app.util;

import spark.Filter;
import spark.Request;
import spark.Response;

import static app.util.RequestUtil.getQueryLocale;

public class Filters {

    public static Filter addTrailingSlashes = (Request request, Response response) -> {
        if (!request.pathInfo().endsWith("/")) {
            response.redirect(request.pathInfo() + "/");
        }
    };

    public static Filter handleLocaleChange = (Request request, Response response) -> {
      if (getQueryLocale(request) != null) {
          request.session().attribute("locale", getQueryLocale(request));
          response.redirect(request.pathInfo());
      }
    };

    public static Filter addGzipHeader = (Request request, Response response) -> {
        response.header("Content-Encoding", "gzip");
    };
}
