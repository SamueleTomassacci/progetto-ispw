package it.uniroma2.dicii.ispw.utils.bean.interfaccia1;

import java.io.File;

public class FotoBean {
    private File immagine;
    public FotoBean(File immagine){
        this.immagine=immagine;
    }
    public File getFoto(){
        return this.immagine;
    }

}
