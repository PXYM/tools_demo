package com.jf.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/13 23:36
 */
@Slf4j
public class ImportRunnable<T> implements Runnable {

    private String filePath;
    private String sheetName;
    private int startIndex;
    private int endIndex;
    private List<T> dataList;
    private final static ReentrantLock lock = new ReentrantLock();

    public ImportRunnable(String filePath, String sheetName, List<T> dataList) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.dataList = dataList;
        this.startIndex = 0;
        this.endIndex = Optional.of(dataList.size()).orElseGet(() -> 0);
    }

    public ImportRunnable(String filePath, String sheetName, int startIndex, int endIndex, List<T> dataList) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataList = dataList;
    }

    @Override
    public void run() {
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        try (ExcelWriter writer = EasyExcel.write(filePath).build()) {
            for (int i = startIndex; i <= endIndex; i += 10000) { // 每次写入10000条数据
                int lastIndex = Math.min(i + 9999, endIndex);
                List<T> subList = dataList.subList(i, lastIndex);
                lock.lock();
                try {
                    writer.write(subList, writeSheet);
                } finally {
                    lock.unlock();
                }
                log.debug("{}文件的{}写入了10000条记录", filePath, sheetName);
            }
            log.info("{}完成{}写入", filePath, sheetName);
        }
    }
}
