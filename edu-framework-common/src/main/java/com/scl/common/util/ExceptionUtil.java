package com.scl.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: chenglu
 * Date: 2020/1/19
 * Description:
 */
public class ExceptionUtil {
    public static String getMessage(Exception e){
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;
        try{
            stringWriter = new StringWriter();
            printWriter= new PrintWriter(stringWriter);
            e.printStackTrace();
            printWriter.flush();
            stringWriter.flush();
        }finally {
            try {
                if (stringWriter!=null)
                    stringWriter.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if(printWriter!=null)
                printWriter.close();
        }
        return stringWriter.toString();
    }
}
