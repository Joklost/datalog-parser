package app.datalog;

import org.deri.iris.Configuration;
import org.deri.iris.EvaluationException;
import org.deri.iris.KnowledgeBase;
import org.deri.iris.api.IKnowledgeBase;
import org.deri.iris.api.basics.IPredicate;
import org.deri.iris.api.basics.IQuery;
import org.deri.iris.api.basics.IRule;
import org.deri.iris.api.terms.IVariable;
import org.deri.iris.compiler.Parser;
import org.deri.iris.compiler.ParserException;
import org.deri.iris.optimisations.magicsets.MagicSets;
import org.deri.iris.storage.IRelation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatalogParser {
    private Parser parser;

    DatalogParser() {
        parser = new Parser();
    }

    public List<String> parse(String program) throws ParserException, EvaluationException {

        if (program == null) return null;
        parser.parse(program);
        Map<IPredicate, IRelation> facts = parser.getFacts();
        List<IRule> rules = parser.getRules();
        List<IQuery> queries = parser.getQueries();

        Configuration conf = new Configuration();
        conf.programOptmimisers.add(new MagicSets());
        IKnowledgeBase knowledgeBase = new KnowledgeBase(facts, rules, conf);

        StringBuilder res = new StringBuilder();

        List<String> result = new ArrayList<>();

        for (IQuery query : queries) {
            List<IVariable> variableBindings = new ArrayList<>();
            IRelation relation = knowledgeBase.execute(query, variableBindings);

            result.add(query.toString());
            result.add(variableBindings.toString());
            result.add("");

            for (int i = 0; i < relation.size(); i++) {
                result.add(relation.get(i).toString());
            }
            result.add("");
        }

        return result;
    }



}
