import vo.JarVO;

import java.util.Set;

public class PrintCallGraph {
    public static void main(String[] args) {
        String classPaths = "/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar:" +
                "/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit-rt.jar:" +
                "/Applications/IntelliJ IDEA CE.app/Contents/plugins/junit/lib/junit5-rt.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/charsets.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/deploy.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/dnsns.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/jaccess.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/localedata.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/nashorn.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunec.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/ext/zipfs.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/javaws.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jce.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jfr.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jfxswt.jar:" +
                "/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home/lib/tools.jar:/Users/wangchao/Downloads/cf-java-client-4.0.1.RELEASE/cloudfoundry-client-reactor/target/test-classes:/Users/wangchao/Downloads/cf-java-client-4.0.1.RELEASE/cloudfoundry-client-reactor/target/classes:/Users/wangchao/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/Users/wangchao/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/wangchao/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.10.0/jackson-databind-2.10.0.jar:/Users/wangchao/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.10.0/jackson-annotations-2.10.0.jar:/Users/wangchao/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.10.0/jackson-core-2.10.0.jar:/Users/wangchao/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.10.0/jackson-datatype-jdk8-2.10.0.jar:/Users/wangchao/.m2/repository/com/github/zafarkhaja/java-semver/0.9.0/java-semver-0.9.0.jar:/Users/wangchao/.m2/repository/com/squareup/okhttp3/mockwebserver/3.14.4/mockwebserver-3.14.4.jar:/Users/wangchao/.m2/repository/com/squareup/okhttp3/okhttp/3.14.4/okhttp-3.14.4.jar:/Users/wangchao/.m2/repository/com/squareup/okio/okio/1.17.2/okio-1.17.2.jar:/Users/wangchao/.m2/repository/io/jsonwebtoken/jjwt/0.9.1/jjwt-0.9.1.jar:/Users/wangchao/.m2/repository/io/projectreactor/reactor-test/3.3.0.RELEASE/reactor-test-3.3.0.RELEASE.jar:/Users/wangchao/.m2/repository/io/projectreactor/reactor-core/3.3.0.RELEASE/reactor-core-3.3.0.RELEASE.jar:/Users/wangchao/.m2/repository/org/reactivestreams/reactive-streams/1.0.3/reactive-streams-1.0.3.jar:/Users/wangchao/.m2/repository/io/projectreactor/netty/reactor-netty/0.9.1.RELEASE/reactor-netty-0.9.1.RELEASE.jar:/Users/wangchao/.m2/repository/io/netty/netty-codec-http/4.1.43.Final/netty-codec-http-4.1.43.Final.jar:/Users/wangchao/.m2/repository/io/netty/netty-codec-http2/4.1.43.Final/netty-codec-http2-4.1.43.Final.jar:/Users/wangchao/.m2/repository/io/netty/netty-handler/4.1.43.Final/netty-handler-4.1.43.Final.jar:/Users/wangchao/.m2/repository/io/netty/netty-handler-proxy/4.1.43.Final/netty-handler-proxy-4.1.43.Final.jar:/Users/wangchao/.m2/repository/io/netty/netty-transport-native-epoll/4.1.43.Final/netty-transport-native-epoll-4.1.43.Final-linux-x86_64.jar:/Users/wangchao/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/wangchao/.m2/repository/org/hamcrest/hamcrest-core/2.1/hamcrest-core-2.1.jar:/Users/wangchao/.m2/repository/org/hamcrest/hamcrest/2.1/hamcrest-2.1.jar:/Users/wangchao/.m2/repository/org/assertj/assertj-core/3.13.2/assertj-core-3.13.2.jar:/Users/wangchao/.m2/repository/org/cloudfoundry/cloudfoundry-client/4.0.1.RELEASE/cloudfoundry-client-4.0.1.RELEASE.jar:/Users/wangchao/.m2/repository/com/squareup/wire/wire-runtime/2.2.0/wire-runtime-2.2.0.jar:/Users/wangchao/.m2/repository/org/cloudfoundry/cloudfoundry-util/4.0.1.RELEASE/cloudfoundry-util-4.0.1.RELEASE.jar:/Users/wangchao/.m2/repository/org/apache/commons/commons-compress/1.19/commons-compress-1.19.jar:/Users/wangchao/.m2/repository/org/atteo/evo-inflector/1.2.2/evo-inflector-1.2.2.jar:/Users/wangchao/.m2/repository/org/immutables/value/2.7.4/value-2.7.4.jar:/Users/wangchao/.m2/repository/org/mockito/mockito-core/3.1.0/mockito-core-3.1.0.jar:/Users/wangchao/.m2/repository/net/bytebuddy/byte-buddy/1.10.2/byte-buddy-1.10.2.jar:/Users/wangchao/.m2/repository/net/bytebuddy/byte-buddy-agent/1.10.2/byte-buddy-agent-1.10.2.jar:/Users/wangchao/.m2/repository/org/objenesis/objenesis/2.6/objenesis-2.6.jar:/Users/wangchao/.m2/repository/org/slf4j/jcl-over-slf4j/1.7.29/jcl-over-slf4j-1.7.29.jar:/Users/wangchao/.m2/repository/org/slf4j/slf4j-api/1.7.29/slf4j-api-1.7.29.jar:/Users/wangchao/.m2/repository/org/springframework/spring-core/5.2.1.RELEASE/spring-core-5.2.1.RELEASE.jar:/Users/wangchao/.m2/repository/org/springframework/spring-jcl/5.2.1.RELEASE/spring-jcl-5.2.1.RELEASE.jar:/Users/wangchao/.m2/repository/org/springframework/spring-test/5.2.1.RELEASE/spring-test-5.2.1.RELEASE.jar:/Users/wangchao/.m2/repository/org/springframework/spring-web/4.3.25.RELEASE/spring-web-4.3.25.RELEASE.jar:/Users/wangchao/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.2.1.RELEASE/spring-boot-starter-logging-2.2.1.RELEASE.jar:/Users/wangchao/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.12.1/log4j-to-slf4j-2.12.1.jar:/Users/wangchao/.m2/repository/org/apache/logging/log4j/log4j-api/2.12.1/log4j-api-2.12.1.jar:/Users/wangchao/.m2/repository/org/slf4j/jul-to-slf4j/1.7.29/jul-to-slf4j-1.7.29.jar:/Users/wangchao/.m2/repository/io/netty/netty-all/4.1.43.Final/netty-all-4.1.43.Final.jar:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar";
        String[] classpath = classPaths.split(":");
        int num = 0;
        for (String c : classpath) {
//            System.out.println(c);
            num++;
        }
//        System.out.println(num);
        JarVO depJar = new JarVO("org.slf4j", "slf4j-api", "1.7.5");
        JarVO depJar1 = new JarVO("org.slf4j", "slf4j-api", "1.7.29");
//        List<String> classes = SootUtil.getJarClasses(depJar.getRepositoryJarPath());
//
//        for (String classsig : classes) {
//            System.out.println(classsig);
//        }

//        for (Object key:System.getProperties().keySet()){
//            System.out.println(System.getProperty((String)key));
//        }

//        System.out.println(depJar.getRepositoryJarPath());
//        System.out.println(SootUtil.getJarClasses(depJar.getRepositoryJarPath()).size());
//        Map<String, ClassVO> allClasses = depJar.getAllClass();
//        for(String clsig: allClasses.keySet()){
//           for (MethodVO m : allClasses.get(clsig).getMethods()){
//               System.out.println(m.getMthdSig());
//           }
//        }
        Set<String> commonMethods = depJar.getCommonMethods(depJar1.getallMethods());
        for (String method : commonMethods) {
            System.out.println(method);
        }

        System.out.println(System.getProperty("user.dir"));
    }
}
