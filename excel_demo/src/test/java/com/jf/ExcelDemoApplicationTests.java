package com.jf;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.exception.ExcelGenerateException;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.jf.pojo.po.User;
import com.jf.utils.ImportRunnable;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class ExcelDemoApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void simpleWrite() {
        String fileName = "F:\\Temp\\simpleWrite.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build()) {
            long startTime = System.currentTimeMillis();
            for (int i = 3; i < 6; i++) {
                System.out.println("第" + i + "表");
                WriteSheet writeSheet = EasyExcel.write(fileName, User.class)
                        .sheet(i, "模板" + i).build();
                excelWriter.write(data(), writeSheet);
            }
            long lastTime = System.currentTimeMillis();
            System.out.println(TimeUnit.MILLISECONDS.toSeconds(lastTime - startTime));
        } catch (ExcelGenerateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程池处理，插入1000_10000数据
     */
    @Test
    public void simpleWrite2Thread() {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        String fileName = "F:\\Temp\\simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 开始时间
        Long startTime = System.currentTimeMillis();
        try (ExcelWriter excelWriter = EasyExcel.write(fileName, User.class).build()) {
            for (int i = 0; i < 10; i++) {
                ImportRunnable<User> run = new ImportRunnable<>(fileName, "sheet" + i, data());
                pool.submit(run);
            }
        } catch (ExcelGenerateException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        while (!pool.isTerminated()) {
            // 等待所有任务执行完成
        }
        // 结束时间
        Long lastTime = System.currentTimeMillis();
        System.out.println("-----" + TimeUnit.MILLISECONDS.toSeconds(lastTime - startTime));


    }

    private List<User> data() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100_0000; i++) {
            User data = new User();
            data.setUid("uid: " + i);
            data.setName("name" + i);
            data.setAge(new Random().nextInt(50));
            data.setBirthday(new Date());
            list.add(data);
        }
        return list;
    }
}
