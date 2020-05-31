package org.jabref.gui;

import org.jabref.gui.menus.FileHistoryMenu;
import org.jabref.logic.util.io.FileHistory;
import org.jabref.preferences.JabRefPreferences;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.*;

//CS304 (manually written) Issue link: https://github.com/JabRef/jabref/issues/6529
public class FileHistoryMenuTest {
    private FileHistoryMenu fileHistory;
    private JabRefFrame jabRefFrame;
    private JabRefPreferences prefs;
    private DialogService dialogService;
    List<Path> files = new ArrayList<Path>();

    @BeforeEach
    void setUp(){
        prefs = JabRefPreferences.getInstance();
        jabRefFrame = mock(JabRefFrame.class);
        dialogService = mock(DialogService.class);
    }

    @Test
    void testNormalNumber1() {
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test1.bib"));
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test2.bib"));
        FileHistory fileHistoryExample = new FileHistory(files);
        prefs.storeFileHistory(fileHistoryExample);
        fileHistory = new FileHistoryMenu(prefs, dialogService, jabRefFrame.getOpenDatabaseAction());
        assertTrue(fileHistory.openFileByKey("1"));
    }

    @Test
    void testNormalNumber2() {
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test1.bib"));
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test2.bib"));
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test3.bib"));
        FileHistory fileHistoryExample = new FileHistory(files);
        prefs.storeFileHistory(fileHistoryExample);
        fileHistory = new FileHistoryMenu(prefs, dialogService, jabRefFrame.getOpenDatabaseAction());
        assertTrue(fileHistory.openFileByKey("3"));
    }

    @Test
    void testOversizeNumber(){
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test1.bib"));
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test2.bib"));
        FileHistory fileHistoryExample = new FileHistory(files);
        prefs.storeFileHistory(fileHistoryExample);
        fileHistory = new FileHistoryMenu(prefs, dialogService, jabRefFrame.getOpenDatabaseAction());
        assertFalse(fileHistory.openFileByKey("5"));
    }

    @Test
    void testMessage(){
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test1.bib"));
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test2.bib"));
        FileHistory fileHistoryExample = new FileHistory(files);
        prefs.storeFileHistory(fileHistoryExample);
        fileHistory = new FileHistoryMenu(prefs, dialogService, jabRefFrame.getOpenDatabaseAction());
        assertFalse(fileHistory.openFileByKey("M"));
    }

    @Test
    void testNullMessage(){
        files.add(FileSystems.getDefault().getPath("C:/TestPath/test1.bib"));
        FileHistory fileHistoryExample = new FileHistory(files);
        prefs.storeFileHistory(fileHistoryExample);
        fileHistory = new FileHistoryMenu(prefs, dialogService, jabRefFrame.getOpenDatabaseAction());
        assertFalse(fileHistory.openFileByKey(null));
    }

}
