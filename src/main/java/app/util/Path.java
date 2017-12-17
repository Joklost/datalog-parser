package app.util;

import lombok.Getter;

public class Path {

    public static class Web {
        @Getter public static final String INDEX = "/datalog/";
        @Getter public static final String PARSE = "/datalog/parse/";
    }

    public static class Template {
        public final static String INDEX = "/velocity/index/index.vm";
        public final static String NOT_FOUND = "/velocity/notfound.vm";
    }
}

