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
}
