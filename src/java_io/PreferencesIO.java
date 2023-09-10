package java_io;

import java.util.prefs.Preferences;

public class PreferencesIO {
	public static void main(String[] args) {
		Preferences prefs = Preferences.userNodeForPackage(PreferencesIO.class);
		System.out.println(prefs.get("name", "default"));
	}
}
