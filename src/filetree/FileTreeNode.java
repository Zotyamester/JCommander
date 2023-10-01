package filetree;

import javax.swing.tree.TreeNode;
import java.io.File;
import java.util.*;

public class FileTreeNode implements TreeNode {

    private FileTreeNode parent;
    private List<FileTreeNode> children = null;
    private File file;

    public FileTreeNode(File descriptor, FileTreeNode parent) {
        if (descriptor == null) {
            throw new IllegalArgumentException("Descriptor shall not be null.");
        }

        this.parent = parent;
        this.file = descriptor;
    }

    public FileTreeNode(File descriptor) {
        this(descriptor, null);
    }

    public boolean rename(String newName) {
        File newFile = new File(file.getParent(), newName);
        if (!newFile.getParent().equals(file.getParent())) {
            return false;
        }

        if (file.renameTo(newFile)) {
            file = newFile;
            return true;
        }
        return false;
    }

    public void reparent(FileTreeNode newParent) {
        if (parent != null) {
            parent.removeChild(this);
        }
        newParent.addChild(this);
    }

    public void addChild(FileTreeNode fileTreeNode) {
        children.add(fileTreeNode);
    }

    public void removeChild(FileTreeNode fileTreeNode) {
        children.remove(fileTreeNode);
    }

    public void lazyLoadChildren() {
        if (file.isFile()) {
            return;
        }

        if (children == null) {
            children = new ArrayList<>();
        } else {
            children.clear();
        }

        File[] filesInDirectory = file.listFiles();
        // even though it technically should never be null, that's pretty much what our guard-clause is for,
        // but in some weird, hacky Windows-tested edge cases, it can indeed happen that a file is seemingly
        // a directory but one cannot access its contents
        if (filesInDirectory == null) {
            return;
        }

        for (File file : filesInDirectory) {
            children.add(new FileTreeNode(file, this));
        }
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if (file.isFile()) {
            return null;
        }

        lazyLoadChildren(); // this way we're always updated on the current directory structure

        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        if (file.isFile()) {
            return 0;
        }

        File[] filesInDirectory = file.listFiles();
        if (filesInDirectory == null) {
            return 0;
        }

        return filesInDirectory.length; // note that this may differ from our current view on the FS (children)
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf((FileTreeNode) node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return file.isFile();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
    }

    @Override
    public String toString() {
        if (parent != null) {
            return file.getName();
        }

        return file.getAbsolutePath();
    }
}
