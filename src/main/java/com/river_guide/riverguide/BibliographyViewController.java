package com.river_guide.riverguide;

import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BibliographyViewController implements Initializable {

    @FXML
    private VBox linksContainer;

    private HashMap<String, String> _links = new HashMap<>() {
        {
            put("La pasión: Desastre ecológico y social",
                    "https://cmiguate.org/la-pasion-desastre-ecologico-y-social/#:~:text=Lo%20que%20sucedi%C3%B3%20en%20el,tanto%20los%20organismos%20se%20asfixiaron");
            put("En Ixcán el rastro municipal contamina con heces y sangre una comunidad",
                    "https://www.prensacomunitaria.org/2021/03/en-ixcan-el-rastro-municipal-contamina-con-heces-y-sangre-una-comunidad/");
            put("The Ocean Cleanup Trials New Interceptor in World’s Most Polluting River",
                    "https://theoceancleanup.com/updates/the-ocean-cleanup-trials-new-interceptor-in-worlds-most-polluting-river/");
            put("Contaminación Del Rio Los Esclavos-1",
                    "https://es.scribd.com/document/351647507/Contaminacion-Del-Rio-Los-Esclavos-1");
            put("Ecocido en el Río La Pasión en Sayaxché, Petén, Guatemala",
                    "https://www.ejatlas.org/print/proyecto-minero-el-corpus-honduras#:~:text=La%20contaminaci%C3%B3n%20del%20r%C3%ADo%20ha,hasta%20el%2030%20de%20junio");
            put("Río Paz se encuentra contaminado con melaza ",
                    "https://amuyshondtdotcom.wordpress.com/2016/05/11/rio-paz-se-encuentra-contaminado-con-melaza-publicacion-tomada-de-prensa-libre-guatemala/");
            put("11 de los 25 ríos más contaminantes de Centroamérica están en Guatemala",
                    "https://revistaviatori.com/notas/11-de-25-rios-contaminantes-guatemala/");
            put("Acciones contribuyen a saneamiento del río Motagua", 
                    "https://dca.gob.gt/noticias-guatemala-diario-centro-america/acciones-contribuyen-a-saneamiento-del-rio-motagua/");
            put("MARN promueve acciones para proteger el río Salamá", 
                    "https://agn.gt/marn-impulsa-acciones-para-proteger-el-rio-samala/");
            put("Colocarán cercas en río Las Vacas para recolectar desechos sólidos", 
                    "https://agn.gt/colocaran-cercas-en-rio-las-vacas-para-recolectar-desechos-solidos/");
            put("La Pasión: Desastre ecológico y social", 
                    "https://cmiguate.org/la-pasion-desastre-ecologico-y-social/");
            put("Río Dulce: Una joya natural a las puertas del Caribe", 
                    "https://www.visitcentroamerica.com/visitar/rio-dulce/");
            put("Río Coyolate recibe 2,500 peces nativos", 
                    "https://icc.org.gt/es/rio-coyolate-recibe-2500-peces-nativos/");
            put("Ministros de Ambiente de El Salvador y Guatemala se reúnen para coordinar acciones en Cuenca del Río Paz", 
                    "https://www.sica.int/busqueda/Noticias.aspx?IDItem=14065&IDCat=3&IdEnt=2&Idm=1&IdmStyle=1");
            put("MARN reforesta cuenca baja del río Los Esclavos en Santa Rosa", 
                    "https://www.marn.gob.gt/marn-reforesta-cuenca-baja-del-rio-los-esclavos-en-santa-rosa/");
            put("Comité acciona para salvar el Río Cahabón", 
                    "https://www.sica.int/busqueda/Noticias.aspx?IDItem=46048&IDCat=2&IdEnt=47");
            put("Ejecutan acciones para el cuidado de la cuenca del río Naranjo", 
                    "https://agn.gt/ejecutan-acciones-para-el-cuidado-de-la-cuenca-del-rio-naranjo/");
            put("Recolectan desechos sólidos en la ribera del río Suchiate", 
                    "https://www.marn.gob.gt/recolectan-desechos-solidos-en-la-ribera-del-rio-suchiate/");
        }
    };
    private HostServices _hostServices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (String linkLabel :
                _links.keySet()) {
            var link = new Hyperlink();
            link.setText(linkLabel);
            link.setOnAction(this::onLinkClick);
            linksContainer.getChildren().add(link);
        }
    }

    private void onLinkClick(ActionEvent e) {
        Hyperlink source =  (Hyperlink) e.getSource();
        if (!_links.containsKey(source.getText())) {
            return;
        }
        _hostServices.showDocument(_links.get(source.getText()));
    }

    public void setHostServices(HostServices hostServices) {
        _hostServices = hostServices;
    }
}
