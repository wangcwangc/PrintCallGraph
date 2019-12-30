package soot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.CallGraphTF;
import soot.Scene;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import util.SootUtil;
import vo.MethodCall;

public class MethodPathCGTF extends CallGraphTF {

//    public JRiskMthdPathCgTf(DepJarJRisk depJarJRisk) {
//        super(depJarJRisk);
//    }
//
//    public JRiskMthdPathCgTf(Set<String> entryMethods) {
//        super(entryMethods);
//    }
//
//    public JRiskMthdPathCgTf(DepJarJRisk depJarJRisk, Set<String> entryMethods) {
//        super(depJarJRisk, entryMethods);
//    }
//
//    public JRiskMthdPathCgTf(DepJarJRisk depJarJRisk, boolean filterusedDepJarParent, Set<String> entryMethods) {
//        super(depJarJRisk, depJarJRisk.getUsedDepJar().getAllParentDepJar(), entryMethods);
//    }

    @Override
    protected void formGraph() {
        if (graph == null) {
            // get call-graph.
            Map<String, Node4Path> name2node = new HashMap<String, Node4Path>();
            List<MethodCall> mthdRlts = new ArrayList<MethodCall>();
            Map<String, String> methodMappingASMMethod = new HashMap<>();

            CallGraph cg = Scene.v().getCallGraph();

            Iterator<Edge> ite = cg.iterator();
            while (ite.hasNext()) {
                Edge edge = ite.next();

                String srcMthdName = edge.src().getSignature();
                String tgtMthdName = edge.tgt().getSignature();

                String srcMethodNameASMsignature = edge.src().getBytecodeSignature();
                String tgtMethodNameASMsignature = edge.tgt().getBytecodeSignature();
                // //TODO1
                // if("<com.fasterxml.jackson.core.JsonFactory: boolean
                // requiresPropertyOrdering()>".equals(tgtMthdName)) {
                // MavenUtil.i().getLog().info("srcMthdName:"+srcMthdName);
                // }
                String srcClsName = edge.src().getDeclaringClass().getName();
                String tgtClsName = edge.tgt().getDeclaringClass().getName();
                if (edge.src().isJavaLibraryMethod() || edge.tgt().isJavaLibraryMethod()) {
                    // filter relation contains javaLibClass
//				} else if (usedJarClses.contains(SootUtil.mthdSig2cls(srcMthdName))
//						&& usedJarClses.contains(SootUtil.mthdSig2cls(tgtMthdName))) {
//					 filter relation inside conflictJar
                } else if (conflictJarClses.contains(SootUtil.mthdSig2cls(srcMthdName))
                        && conflictJarClses.contains(SootUtil.mthdSig2cls(tgtMthdName))) {
                    // filter relation inside conflictJar 过滤掉conflictJar中的类
                } else {
                    if (edge.src().isConcrete() || edge.tgt().isConcrete()) {
//						if (riskMthds.contains(srcMthdName)) {
//							System.out.println(edge.src().getSignature());
//							System.out.println(edge.src().getActiveBody().getAllUnitBoxes());
//						}
                        if (!name2node.containsKey(srcMthdName)) {
                            name2node.put(srcMethodNameASMsignature,
                                    new Node4Path(srcMethodNameASMsignature, isHostClass(srcClsName) && !edge.src().isPrivate(),
                                            riskMthds.contains(srcMthdName)));
                        }
                        if (!name2node.containsKey(tgtMthdName)) {
                            name2node.put(tgtMethodNameASMsignature,
                                    new Node4Path(tgtMethodNameASMsignature, isHostClass(tgtClsName) && !edge.tgt().isPrivate(),
                                            riskMthds.contains(tgtMthdName)));
                        }
                        mthdRlts.add(new MethodCall(srcMethodNameASMsignature, tgtMethodNameASMsignature));
                        //保存 risk method 的 asm版本
                        if (riskMthds.contains(srcMthdName)) {
                            methodMappingASMMethod.put(srcMethodNameASMsignature, srcMthdName);
                        } else if (riskMthds.contains(tgtMthdName)) {
                            methodMappingASMMethod.put(tgtMethodNameASMsignature, tgtMthdName);
                        }
                    }
                }
            }
            graph = new Graph4Path(name2node, mthdRlts, methodMappingASMMethod);
        }
    }

    @Override
    protected void initMthd2branch() {

    }

}
