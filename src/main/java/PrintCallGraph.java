import soot.*;
import util.MySortedMap;
import util.SootUtil;
import vo.ClassVO;
import vo.JarVO;

import java.io.*;
import java.util.*;

public class PrintCallGraph {
    //TODO
    static String riskMethod = "testInvalidChecksum";

    public static void main(String[] args) throws IOException {
        Map<String, ClassVO> result = HostClassAna.i().deconstruct(Arrays.asList("/Users/wangchao/Downloads/WZWave-master/target/test-classes"));
        for (String cls : result.keySet()){
            System.out.println(cls);
            System.out.println(result.get(cls).toString());
        }
    }
//        //TODO
//        String entryClass = "com.whizzosoftware.wzwave.codec.ZWaveFrameDecoderTest";
//        //TODO
////        System.getProperty("java.class.path");
//        String classPaths = "/Users/wangchao/Downloads/WZWave-master/target/test-classes:/Users/wangchao/Downloads/WZWave-master/target/classes:" +
//                "/Users/wangchao/.m2/repository/io/netty/netty-buffer/4.0.0.CR9/netty-buffer-4.0.0.CR9.jar";
//        String[] classpath = classPaths.split(":");
//
//        Set<String> methods = new HashSet<>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader("commonMethods.txt"));
//            String line = reader.readLine();
//            while (line != null) {
//                if (!line.equals("")) {
//                    methods.add(line.split("@@")[0]);
//                }
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        MethodPathCGTF methodPathCGTF = new MethodPathCGTF(entryClass, methods);
//
//        Graph4Path graph4Path = (Graph4Path) JarAna.i().getGraph(classpath, methodPathCGTF);
//        // Graph4path pathGraph = jarRisk.getGraph4mthdPath();
//        Set<String> hostNds = graph4Path.getHostNodes();
////			GraphPrinter.printGraph(pathGraph, UserConf.getOutDir4Mac() + "graph_mthdPath.txt", hostNds);
//
//        Map<String, IBook> books = new Dog(graph4Path).findRlt(hostNds, 100,
//                Dog.Strategy.NOT_RESET_BOOK);
//
//        MySortedMap<Integer, Record4Path> dis2records = new MySortedMap<Integer, Record4Path>();
//        // List<Record4mthdPath> records = new ArrayList<Record4mthdPath>();
//
//        for (String topMthd : books.keySet()) {
//            if (hostNds.contains(topMthd)) {
//                Book4Path book = (Book4Path) (books.get(topMthd));
//                for (IRecord iRecord : book.getRecords()) {
//                    Record4Path record = (Record4Path) iRecord;
//                    dis2records.add(record.getPathlen(), record);
//                }
//            }
//        }
//        if (dis2records.size() > 0) {
//            PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter("test.txt")));
//            for (Record4Path record : dis2records.flat()) {
//                if (record.getPathStr().split("\\n")[0].contains(riskMethod)) {
//                    printer.println("pathLen:" + record.getPathlen() + "\n" + addJarPath(record.getPathStr()));
//                }
//            }
//            printer.close();
//        }
//    }
//
//    private static String addJarPath(String mthdCallPath) {
//        StringBuilder sb = new StringBuilder();
//        String[] mthds = mthdCallPath.split("\\n");
//        for (int i = 0; i < mthds.length - 1; i++) {
//            // last method is risk method,don't need calculate.
//            String mthd = mthds[i];
//            sb.append(mthd + "\n");
//        }
//        sb.append(mthds[mthds.length - 1]);
//        return sb.toString();
//    }
}
