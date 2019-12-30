package soot;

import soot.jimple.toolkits.callgraph.CHATransformer;

import java.util.HashMap;
import java.util.Map;

public class CallGraphTF extends SceneTransformer {
    protected void internalTransform(String s, Map<String, String> map) {
        Map<String, String> cgMap = new HashMap<String, String>();

        cgMap.put("enabled", "true");
        cgMap.put("apponly", "true");
        cgMap.put("all-reachable", "true");

        CHATransformer.v().transform("wjtp", cgMap);

        formGraph();
    }

    private void formGraph() {

    }
}
