package vo;

import soot.JarAna;

import java.io.File;
import java.util.*;

public class JarVO {
    private String groupId;
    private String artifactId;
    private String version;
    private Map<String, ClassVO> allClass;// all class in jar
    private Set<String> allMethods;

    public JarVO(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        allClass = new HashMap<String, ClassVO>();
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRepositoryJarPath() {
        return "/Users/wangchao/.m2/repository/" +
                groupId.replace(".", "/") + File.separator +
                artifactId.replace(".", "/") + File.separator +
                version + File.separator +
                artifactId + "-" + version + ".jar";
    }

    /**
     * 得到这个jar所有类的集合
     *
     * @return
     */
    public Map<String, ClassVO> getAllClass() {
//        if (allClass == null) {
        List<String> paths = new ArrayList<String>();
        paths.add(getRepositoryJarPath());
        allClass = JarAna.i().deconstruct(paths);
//                if (allClass.size() == 0) {
//                    MavenUtil.i().getLog().warn("get empty allClass for " + toString());
//                }
//                for (ClassVO clsVO : allClass.values()) {
//                    clsVO.setDepJar(this);
//                }
//            }
//        }
        return allClass;
    }

    public ClassVO getClassVO(String clsSig) {
        return getAllClass().get(clsSig);
    }

    /**
     * 得到这个jar的所有方法
     *
     * @return
     */
    public Set<String> getallMethods() {
        if (allMethods == null) {
            allMethods = new HashSet<String>();
            for (ClassVO cls : getAllClass().values()) {
                for (MethodVO mthd : cls.getMethods()) {
                    allMethods.add(mthd.getMthdSig());
                }
            }
        }
        return allMethods;
    }

    public Set<String> getCommonMethods(Collection<String> entryMethods) {
        Set<String> commonMethods = new HashSet<String>();
        for (String testMethod : entryMethods) {
            if (this.containMethod(testMethod)) {
                commonMethods.add(testMethod);
            }
        }
        return commonMethods;
    }

    public boolean containMethod(String mthd) {
        return getallMethods().contains(mthd);
    }
}
