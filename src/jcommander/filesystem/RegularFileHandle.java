package jcommander.filesystem;

import jcommander.ResourceFactory;
import jcommander.settings.IconType;

import javax.swing.*;
import java.io.File;

public class RegularFileHandle extends FileHandle {

    public RegularFileHandle(File file) {
        super(file);
    }

    @Override
    public String getName() {
        return file.getName();
    }

    @Override
    public Handle[] getChildren() {
        return new Handle[0];
    }

    @Override
    public IconType getAssociatedIcon() {
        return IconType.FILE;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }
}
