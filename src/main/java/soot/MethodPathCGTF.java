package soot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import vo.MethodCall;

public class MethodPathCGTF extends CallGraphTF {

    public MethodPathCGTF(String entryClass, Set<String> riskRethods) {
        super(entryClass, riskRethods);
    }

    @Override
    protected void formGraph() {
        if (graph == null) {
            System.out.println("start to generate call graph");
            // get call-graph.
            Map<String, Node4Path> name2node = new HashMap<>();
            List<MethodCall> mthdRlts = new ArrayList<>();

            CallGraph cg = Scene.v().getCallGraph();

            Iterator<Edge> ite = cg.iterator();
            while (ite.hasNext()) {
                Edge edge = ite.next();
                String srcMthdName = edge.src().getSignature();
                String tgtMthdName = edge.tgt().getSignature();

                String srcClsName = edge.src().getDeclaringClass().getName();
                String tgtClsName = edge.tgt().getDeclaringClass().getName();
                if (edge.src().isJavaLibraryMethod() || edge.tgt().isJavaLibraryMethod()) {
                } else {
                    if (!name2node.containsKey(srcMthdName)) {
                        name2node.put(srcMthdName, new Node4Path(srcMthdName, isHostClass(srcClsName),
                                riskMethods.contains(srcMthdName)));
                    }
                    if (!name2node.containsKey(tgtMthdName)) {
                        name2node.put(tgtMthdName, new Node4Path(tgtMthdName, isHostClass(tgtClsName),
                                riskMethods.contains(tgtMthdName)));
                    }
                    mthdRlts.add(new MethodCall(srcMthdName, tgtMthdName));
                }
            }
            graph = new Graph4Path(name2node, mthdRlts);
            System.out.println("end graph");
        }
    }

    @Override
    protected void initMthd2branch() {

    }

}
