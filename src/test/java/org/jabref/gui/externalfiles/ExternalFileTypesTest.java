package org.jabref.gui.externalfiles;

import java.util.ArrayList;
import java.util.List;

import org.jabref.gui.externalfiletype.ExternalFileType;
import org.jabref.gui.externalfiletype.ExternalFileTypes;
import org.jabref.gui.externalfiletype.StandardExternalFileType;
import org.jabref.Globals;
import org.jabref.preferences.JabRefPreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalFileTypesTest {
    private ExternalFileTypes myExternalFileType;

    @BeforeEach
    void setup() {
        final JabRefPreferences preferences = JabRefPreferences.getInstance();
        Globals.prefs = preferences;

        myExternalFileType = ExternalFileTypes.getInstance();
        List<ExternalFileType> list = new ArrayList<ExternalFileType>();
        list.add(StandardExternalFileType.PDF);
        list.add(StandardExternalFileType.Word);
        list.add(StandardExternalFileType.Excel);

    }

    @Test
    void getExternalFileTypeByNamePositiveTest(){
        assertEquals(myExternalFileType.getExternalFileTypeByName("pdf").get(),StandardExternalFileType.PDF);
        assertEquals(myExternalFileType.getExternalFileTypeByName("doc").get(),StandardExternalFileType.Word);
        assertEquals(myExternalFileType.getExternalFileTypeByName("xls").get(),StandardExternalFileType.Excel);
    }

    @Test
    void getExternalFileTypeByNameNegativeTest(){
        assertEquals(myExternalFileType.getExternalFileTypeByName("ppt").get().getName(),"");
    }

}
