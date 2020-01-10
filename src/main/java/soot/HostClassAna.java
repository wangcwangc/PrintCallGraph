package soot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.Transform;
import util.SootUtil;
import vo.ClassVO;

import static soot.SootClass.HIERARCHY;

public class HostClassAna extends SootAna {
    public static long runtime = 0;
    private static HostClassAna instance;

    private HostClassAna() {

    }

    public static HostClassAna i() {
        if (instance == null) {
            instance = new HostClassAna();
        }
        return instance;
    }

    /**
     * 解析jar包
     *
     * @param jarFilePath jar包文件的路径
     * @return
     */
    public Map<String, ClassVO> deconstruct(List<String> jarFilePath) {
//		MavenUtil.i().getLog().info("use soot to deconstruct " + jarFilePath);

        long startTime = System.currentTimeMillis();

        List<String> args = getArgs(jarFilePath.toArray(new String[0]));    //执行命令
        if (args.size() == 0) {
            return new HashMap<String, ClassVO>();
        } else {
            DsTransformer transformer = new DsTransformer(jarFilePath);
            PackManager.v().getPack("wjtp").add(new Transform("wjtp.myTrans", transformer));
//			SootUtil.modifyLogOut();
            soot.Main.main(args.toArray(new String[0]));
            Map<String, ClassVO> clses = transformer.getClsTb();
            soot.G.reset();

            runtime = runtime + (System.currentTimeMillis() - startTime) / 1000;
            return clses;
        }
    }

    protected void addCgArgs(List<String> argsList) {
        argsList.addAll(Arrays.asList(new String[]{"-p", "cg", "off",}));
    }

}

class DsTransformer extends SceneTransformer {
    private Map<String, ClassVO> clsTb;
    private List<String> jarPaths;

    public DsTransformer(List<String> jarPaths) {
        this.jarPaths = jarPaths;
    }

    @Override
    protected void internalTransform(String phaseName, Map<String, String> options) {
        clsTb = SootUtil.getClassTb(this.jarPaths);
    }

    public Map<String, ClassVO> getClsTb() {
        return clsTb;
    }

}
