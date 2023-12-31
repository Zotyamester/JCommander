package hu.bme.jcommander.filesystem;

import hu.bme.jcommander.settings.IconType;

import java.io.File;

public class RegularFileHandle extends FileHandle {

    /**
     * Constructs a RegularFileHandle representing a regular file with the specified {@code file}.
     *
     * @param file the {@code File} object representing the regular file
     */
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
