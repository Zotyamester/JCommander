package jcommander.pane;

import java.io.File;

public interface SelectionController extends Controller {

    File[] getSelectedFiles();
}
