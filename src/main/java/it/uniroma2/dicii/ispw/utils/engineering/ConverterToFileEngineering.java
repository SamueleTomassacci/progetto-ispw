package it.uniroma2.dicii.ispw.utils.engineering;

import it.uniroma2.dicii.ispw.utils.bean.ConverterBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConverterToFileEngineering {
    private static final int DEFAULTBUFFERSIZE = 8192*4;

    public void fromInputStreamToFile(ConverterBean converterBean) throws SystemException {
        InputStream inputStream = converterBean.getInputStream();
        File file = converterBean.getFile();

        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULTBUFFERSIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            SystemException ex = new SystemException();
            ex.initCause(e);
            throw ex;
        }
    }
}
