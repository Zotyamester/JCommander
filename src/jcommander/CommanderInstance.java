package jcommander;

import jcommander.pane.WorkPane;

import javax.swing.*;
import java.awt.*;

import static jcommander.ResourceFactory.getIcon;

public class CommanderInstance {

    private final JFrame frame;
    private WorkPane activePane;
    private final WorkPane paneA;
    private final WorkPane paneB;

    public CommanderInstance() {
        frame = new JFrame("JCommander");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        createMenuBar();
        createTopBar(frame);

        paneA = new WorkPane();
        frame.add(paneA, BorderLayout.WEST);

        createCenterBar(frame);

        paneB = new WorkPane();
        frame.add(paneB, BorderLayout.EAST);

        activePane = paneA; // by default, paneA is in focus

        frame.pack();
        frame.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New Window"));
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }

    private void createTopBar(Container container) {
        JToolBar topBar = new JToolBar(SwingConstants.HORIZONTAL);
        topBar.setFloatable(false);
        JButton refresh = new JButton();
        refresh.setIcon(getIcon("refresh.png"));
        refresh.addActionListener(e -> {
            paneA.refresh();
            paneB.refresh();
        });
        topBar.add(refresh);
        JButton find = new JButton();
        find.setIcon(getIcon("find.png"));
        find.addActionListener(e -> openFindDialog());
        topBar.add(find);
        JButton previous = new JButton();
        previous.setIcon(getIcon("left.png"));
        previous.addActionListener(e -> activePane.selectPrevious());
        topBar.add(previous);
        JButton next = new JButton();
        next.setIcon(getIcon("right.png"));
        next.addActionListener(e -> activePane.selectNext());
        topBar.add(next);
        container.add(topBar, BorderLayout.NORTH);
    }

    private void createCenterBar(Container container) {
        JToolBar centerBar = new JToolBar(SwingConstants.VERTICAL);
        centerBar.setFloatable(false);
        JButton copy = new JButton();
        copy.setIcon(getIcon("copy.png"));
        copy.addActionListener(e -> issueCopyOperation());
        centerBar.add(copy);
        JButton move = new JButton();
        move.setIcon(getIcon("move.png"));
        move.addActionListener(e -> issueMoveOperation());
        centerBar.add(move);
        container.add(centerBar, BorderLayout.CENTER);
    }

    private void openFindDialog() {
    }

    private void issueCopyOperation() {
    }

    private void issueMoveOperation() {
    }
}
