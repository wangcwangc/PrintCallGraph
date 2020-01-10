package soot;

import java.util.Arrays;
import java.util.List;

public class JarAna extends SootAna {
    private static JarAna instance = new JarAna();

    private JarAna() {

    }

    public static JarAna i() {
        return instance;
    }

    public IGraph getGraph(String[] jarFilePaths, CallGraphTF transformer) {
        IGraph graph = null;
        try {

            PackManager.v().getPack("wjtp").add(new Transform("wjtp.myTrans", transformer));
            soot.Main.main(getArgs(jarFilePaths).toArray(new String[0]));

            graph = transformer.getGraph();

        } catch (Exception e) {
            System.out.println("cg error : " + e);
        }
        soot.G.reset();
        return graph;
    }

    @Override
    protected void addCgArgs(List<String> argsList) {
        argsList.addAll(Arrays.asList(new String[]{"-p", "cg", "off",}));
    }
}


