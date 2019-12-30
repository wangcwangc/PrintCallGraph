package soot;

import util.Conf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Book4Path extends IBook {
    Set<String> visitedFathes;//record father who copy book from this node,when all father has visited,this book can be deleted.

    public Book4Path(Node4Path node) {
        super(node);
        this.records = new ArrayList<IRecord>();
        visitedFathes = new HashSet<String>();
    }

    @Override
    public void afterAddAllChildren() {
        if (getNode().isRisk()) {
            this.records.add(getNode().formNewRecord());
        }
    }

    private Node4Path getNode() {
        return (Node4Path) this.node;
    }

    @Override
    public void addChild(IBook doneChildBook) {
        for (IRecord iRecord : doneChildBook.getRecords()) {
            Record4Path record = (Record4Path) iRecord;
            addRecord(record.getRiskMethod(), this.getNodeName() + "\n" + record.getPathStr(), record.getPathlen() + 1);
        }
    }

    private void addRecord(String riskMthd, String pathStr, int length) {
        if (Conf.findAllpath) {
            this.records.add(new Record4Path(riskMthd, pathStr, length));
        } else {//find shortest path 找最短路径
            for (IRecord iRecord : this.records) {
                Record4Path record = (Record4Path) iRecord;
                if (riskMthd.equals(record.getRiskMethod())) {
                    if (length < record.getPathlen()) {
                        record.setPathStr(pathStr);
                        record.setPathlen(length);
                    }
                    return;
                }
            }
            this.records.add(new Record4Path(riskMthd, pathStr, length));
        }
    }

}
