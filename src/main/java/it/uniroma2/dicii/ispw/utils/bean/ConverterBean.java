package it.uniroma2.dicii.ispw.utils.bean;

import java.io.File;
import java.io.InputStream;

public class ConverterBean {
    private InputStream inputStream;
    private File file;

    public ConverterBean(InputStream inputStream, File file) {
        this.inputStream = inputStream;
        this.file = file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
