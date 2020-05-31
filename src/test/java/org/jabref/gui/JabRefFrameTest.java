package org.jabref.gui;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

//CS304 (manually written) Issue link: https://github.com/JabRef/jabref/issues/6527
public class JabRefFrameTest {

    private JabRefFrame jabRefFrame;
    private TabPane tabbedPane;

    @BeforeEach
    void setUp() {
        jabRefFrame = mock(JabRefFrame.class);
        tabbedPane = jabRefFrame.getTabbedPane();
    }

    @Test
    void closeCurrentDatabase() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Tab tab1 = new Tab("lib1.bib", new StackPane());
        Tab tab2 = new Tab("lib2.bib", new StackPane());
        Tab tab3 = new Tab("lib3.bib", new StackPane());
        Tab tab4 = new Tab("lib4.bib", new StackPane());
        tabbedPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        SingleSelectionModel<Tab> selectionModel = tabbedPane.getSelectionModel();
        selectionModel.select(tab1);
        Class<?> innerClazz = Class.forName("JabRefFrame$CloseDatabaseAction");
        Constructor<?> constructor = innerClazz.getDeclaredConstructor(JabRefFrame.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(jabRefFrame);
        Method m = innerClazz.getDeclaredMethod("execute");
        m.invoke(o);
        assertFalse(tabbedPane.getTabs().contains(tab1));
        assertEquals(3, tabbedPane.getTabs().size());
    }

    @Test
    void closeOthersDatabase() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Tab tab1 = new Tab("lib1.bib", new StackPane());
        Tab tab2 = new Tab("lib2.bib", new StackPane());
        Tab tab3 = new Tab("lib3.bib", new StackPane());
        Tab tab4 = new Tab("lib4.bib", new StackPane());
        tabbedPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        SingleSelectionModel<Tab> selectionModel = tabbedPane.getSelectionModel();
        selectionModel.select(tab1);
        Class<?> innerClazz = Class.forName("JabRefFrame$CloseOthersDatabaseAction");
        Constructor<?> constructor = innerClazz.getDeclaredConstructor(JabRefFrame.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(jabRefFrame);
        Method m = innerClazz.getDeclaredMethod("execute");
        m.invoke(o);
        assertTrue(tabbedPane.getTabs().contains(tab1));
        assertEquals(1, tabbedPane.getTabs().size());
    }

    @Test
    void closeAllDatabase() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Tab tab1 = new Tab("lib1.bib", new StackPane());
        Tab tab2 = new Tab("lib2.bib", new StackPane());
        Tab tab3 = new Tab("lib3.bib", new StackPane());
        Tab tab4 = new Tab("lib4.bib", new StackPane());
        tabbedPane.getTabs().addAll(tab1, tab2, tab3, tab4);
        SingleSelectionModel<Tab> selectionModel = tabbedPane.getSelectionModel();
        selectionModel.select(tab1);
        Class<?> innerClazz = Class.forName("JabRefFrame$CloseAllDatabaseAction");
        Constructor<?> constructor = innerClazz.getDeclaredConstructor(JabRefFrame.class);
        constructor.setAccessible(true);
        Object o = constructor.newInstance(jabRefFrame);
        Method m = innerClazz.getDeclaredMethod("execute");
        m.invoke(o);
        assertEquals(0, tabbedPane.getTabs().size());
    }

}
