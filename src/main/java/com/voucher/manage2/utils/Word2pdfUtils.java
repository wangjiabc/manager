package com.voucher.manage2.utils;

import cn.hutool.core.util.IdUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.voucher.manage2.constant.SystemConstant;

import java.io.File;


public class Word2pdfUtils {
    public static String wToPdfChange(String wordFile){//wordFile word 的路径  //pdfFile pdf 的路径
        ComThread.InitSTA();
        String pdfFileName = IdUtil.simpleUUID()+SystemConstant.PDF_SUFFIX;
        String pdfFile = SystemConstant.START_WORD_PATH+pdfFileName ;
        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        // long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            System.out.println("打开文件: " + wordFile);
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(pdfFile);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + pdfFile);
            Dispatch.call(document, "SaveAs", pdfFile, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
        }catch(Exception e) {
            System.out.println("转换失败"+e.getMessage());
        }finally {
            // 关闭office
            app.invoke("Quit", new Variant());
            ComThread.Release();

        }

        return pdfFileName;
    }
/*    public static void main(String[] args) {
        String word = "src\\main\\java\\com\\voucher\\docx\\01.docx";
        String name = "02".concat(".pdf");
        String pdf = FileUtils.getFileUrlPath(name);
        wToPdfChange(word, pdf);
    }*/
}
