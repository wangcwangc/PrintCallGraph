package soot;

import java.util.*;

import soot.SceneTransformer;
import soot.jimple.toolkits.callgraph.CHATransformer;

/**
 * to get call-graph. 得到call-graph
 *
 * @author asus
 */
public abstract class CallGraphTF extends SceneTransformer {

    protected String entryClass; // 入口类集合
    protected Set<String> riskMethods; // 风险方法集合
    protected IGraph graph;

    public CallGraphTF(String entryClass, Set<String> riskRethods) {
        super();
        this.entryClass = entryClass;
        this.riskMethods = riskRethods;
    }


    @Override
    protected void internalTransform(String arg0, Map<String, String> arg1) {

        Map<String, String> cgMap = new HashMap<String, String>();

        cgMap.put("enabled", "true");
        cgMap.put("apponly", "true");
        cgMap.put("all-reachable", "true");

        initMthd2branch();

        CHATransformer.v().transform("wjtp", cgMap);

        formGraph();

    }

    protected abstract void initMthd2branch();

    protected abstract void formGraph();

    protected boolean isHostClass(String clsName) {
        return entryClass.equals(clsName);
    }

    public IGraph getGraph() {
        return graph;
    }

}