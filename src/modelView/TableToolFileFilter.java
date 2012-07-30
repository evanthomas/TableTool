package modelView;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TableToolFileFilter extends FileFilter {


	private String extension;
	private String description;
	
	public TableToolFileFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String ext = getExtension(f);
		if (ext != null) {
			if (ext.equals(extension)) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	private static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 &&  i < s.length() - 1) {
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

}
