package com.voucher.manage2.task;

import com.voucher.manage2.constant.SystemConstant;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DeleteOnTime {

    @Scheduled(cron = "0 0 4 * * ?")
    public void consoleInfo() {
        //每天4点执行一次定时任务
        File[] files = new File(SystemConstant.START_WORD_PATH).listFiles();
        //File file = new File(SystemConstant.START_WORD_PATH);
        //File[] files = file.listFiles();
        for (File caheFile : files) {
            caheFile.delete();
        }
    }

}
