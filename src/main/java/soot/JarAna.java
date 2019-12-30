package soot;

import java.util.Arrays;
import java.util.List;

import soot.PackManager;
import soot.Transform;
import util.SootUtil;

public class JarAna extends SootAna {
    private static JarAna instance = new JarAna();

    private JarAna() {

    }

    public static JarAna i() {
        return instance;
    }

    public IGraph getGraph(String[] jarFilePaths, CallGraphTF transformer) {
//		MavenUtil.i().getLog().info("use soot to compute reach methods for " + depJarJRisk.toString());
        IGraph graph = null;
        long start = System.currentTimeMillis();
        try {

            PackManager.v().getPack("wjtp").add(new Transform("wjtp.myTrans", transformer));

            soot.Main.main(getArgs(jarFilePaths).toArray(new String[0]));

            graph = transformer.getGraph();

        } catch (Exception e) {
        }
        soot.G.reset();
        long runtime = (System.currentTimeMillis() - start) / 1000;
        return graph;
    }

    @Override
    protected void addCgArgs(List<String> argsList) {
        argsList.addAll(Arrays.asList(new String[]{"-p", "cg", "off",}));
    }
}


