package soot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.PackManager;
import soot.SceneTransformer;
import soot.Transform;

public class JarAna extends SootAna {
    public static long runtime = 0;
    private static JarAna instance = new JarAna();

    private JarAna() {

    }

    public static JarAna i() {
        if (instance == null) {
            instance = new JarAna();
        }
        return instance;
    }

    /**
     * 解析jar包
     *
     * @param jarFilePath jar包文件的路径
     * @return
     */
    public void deconstruct(List<String> jarFilePath) {
//		MavenUtil.i().getLog().info("use soot to deconstruct " + jarFilePath);

        long startTime = System.currentTimeMillis();

        List<String> args = getArgs(jarFilePath.toArray(new String[0]));    //执行命令

        CallGraphTF transformer = new CallGraphTF();

        PackManager.v().getPack("wjtp").add(new Transform("wjtp.myTrans", transformer));

//			SootUtil.modifyLogOut();

        soot.Main.main(args.toArray(new String[0]));
        soot.G.reset();

        runtime = runtime + (System.currentTimeMillis() - startTime) / 1000;
    }

    protected void addCgArgs(List<String> argsList) {
        argsList.addAll(Arrays.asList(new String[]{"-p", "cg", "off",}));
    }

}