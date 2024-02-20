import it.uniroma2.dicii.ispw.controller.controller_applicativo.decorator.AggiungiCampoControllerApplicativoBase;
import it.uniroma2.dicii.ispw.utils.bean.CampoBean;
import it.uniroma2.dicii.ispw.utils.exceptions.SystemException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
  Test di Samuele Tomassacci
 */
 class TestAggiungiCampoControllerApplicativoBase {

    @Test
   void testGetNumeroMax() throws SystemException {
        CampoBean campoBean =new CampoBean();
        campoBean.setIndirizzo("Via Rossi 23");

        AggiungiCampoControllerApplicativoBase controller= new AggiungiCampoControllerApplicativoBase();

        assertEquals(2,controller.getNumeroMax(campoBean));
    }
}
