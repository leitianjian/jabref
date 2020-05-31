package org.jabref.gui.externalfiles;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jabref.gui.externalfiletype.ExternalFileType;
import org.jabref.gui.externalfiletype.ExternalFileTypes;
import org.jabref.gui.externalfiletype.StandardExternalFileType;
import org.jabref.Globals;
import org.jabref.gui.externalfiletype.UnknownExternalFileType;
import org.jabref.preferences.JabRefPreferences;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalFileTypesTest {
    private ExternalFileTypes myExternalFileType;

    @BeforeEach
    void setup() throws Exception {
        final JabRefPreferences preferences = JabRefPreferences.getInstance();

        myExternalFileType = ExternalFileTypes.getInstance();
        Set<ExternalFileType> list = new TreeSet<ExternalFileType>();
        list.add(StandardExternalFileType.PDF);
        list.add(StandardExternalFileType.Word);
        list.add(StandardExternalFileType.Excel);
        Class c = myExternalFileType.getClass();
        Field f = c.getDeclaredField("externalFileTypes");
        f.setAccessible(true);
        f.set(myExternalFileType, list);
    }

    @Test
    void getExternalFileTypeByNamePositiveTest(){
        assertEquals(myExternalFileType.getExternalFileTypeByName("PDF").get(),StandardExternalFileType.PDF);
        assertEquals(myExternalFileType.getExternalFileTypeByName("Word").get(),StandardExternalFileType.Word);
        assertEquals(myExternalFileType.getExternalFileTypeByName("Excel").get(),StandardExternalFileType.Excel);
    }

    @Test
    void getExternalFileTypeByNameNegativeTest(){
        assertEquals(myExternalFileType.getExternalFileTypeByName("Text").get() instanceof UnknownExternalFileType,true);
    }


}
