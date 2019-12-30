package soot;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import soot.SceneTransformer;
import soot.jimple.toolkits.callgraph.CHATransformer;

/**
 * to get call-graph. 得到call-graph
 *
 * @author asus
 */
public abstract class CallGraphTF extends SceneTransformer {

    // private DepJarJRisk depJarJRisk;
    protected Set<String> entryClses; // 入口类集合
    protected Set<String> conflictJarClses; // 冲突jar类集合
    protected Set<String> usedJarClses; // 使用的jar类集合
    protected Set<String> riskMthds; // 风险方法集合
    protected Set<String> rchMthds;
    protected IGraph graph;
    protected Map<String, Integer> mthd2branch;
    protected Set<String> parentDepJarClasses;

    public CallGraphTF() {
        super();
//        entryClses = DepJars.i().getHostDepJar().getAllCls(true);
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
        return entryClses.contains(clsName);
    }

//	protected boolean isHostClassNoSameJar(String clsName) {
//		return !LibCopyInfo.isLibCopy(MavenUtil.i().getProjectCor(), clsName);
//	}

    public IGraph getGraph() {
        return graph;
    }

}