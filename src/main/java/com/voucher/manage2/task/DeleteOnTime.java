package com.voucher.manage2.task;

import com.voucher.manage2.constant.SystemConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DeleteOnTime {


    @Scheduled(cron = "0 0 4 * * ?")  //每天4点执行一次定时任务
    public void consoleInfo(){
        System.out.println("定时任务");
        //File[] files = new File(SystemConstant.START_WORD_PATH).listFiles();
        File file = new File(SystemConstant.START_WORD_PATH);
        String[] listFile = file.list();
        for (String file1 : listFile) {
            File file2 = new File(file + file1);
            file2.delete();
        }
    }

}
