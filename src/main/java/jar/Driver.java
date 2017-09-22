package main.java.jar;

import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	// REMEMBER TO CHAGE , TO . for the UAPM file and use txt to do this change
	public static ArrayList<Sheet> missingNames;

	public static void main(String[] args) throws IOException {
		// Reading Nlyte XLSX file
		Reading nlyte = new ReadingNlyte();
		nlyte.readLines();
		ArrayList<Sheet> NlyteInfo = nlyte.getList();

		// Reading Asset Management CVS
		Reading uapm = new HardwareReading();
		uapm.readLines();
		ArrayList<Sheet> HardwareInfo = uapm.getList();

		// Missing Asset Tags
		missingNames = new ArrayList<>();

		// =======================================================================
		// Comparing Asset Tag Info for both Nlyte -> UAPM
		compareName(NlyteInfo, HardwareInfo);
		// =======================================================================
		// Creating CSV files all of the lists
		WriteCSV write = new WriteCSV(uapm.getHeader(), missingNames, "Missing.csv");
		write.wirte();
	}

	// Comparing Tags
	public static void compareName(ArrayList<Sheet> nlyte, ArrayList<Sheet> hrdwr) {
		// Putting a copy of all uapm to missingTags
		// missingTags.addAll(uapm);
		missingNames = hrdwr; // to find duplicates
		// If tag was found or not
		boolean[] found = { false, false, false, false, false, false, false, false, false, false };
		for (int i = 0; i != hrdwr.size(); ++i) {
			for (int j = 0; j != nlyte.size(); ++j) {
				// If the name is not found add it to the list
				HardwareSheet hrdw = (HardwareSheet) hrdwr.get(i);
				if (hrdw.HostName1().equals(nlyte.get(j).HostName())) {
					found[0] = true;
				}
				if (hrdw.HostName2().equals(nlyte.get(j).HostName())) {
					found[1] = true;
				}
				if (hrdw.Hop1().equals(nlyte.get(j).HostName())) {
					found[2] = true;
				}
				if (hrdw.Hop2().equals(nlyte.get(j).HostName())) {
					found[3] = true;
				}
				if (hrdw.Hop3().equals(nlyte.get(j).HostName())) {
					found[4] = true;
				}
				if (hrdw.Hop4().equals(nlyte.get(j).HostName())) {
					found[5] = true;
				}
				if (hrdw.Hop5().equals(nlyte.get(j).HostName())) {
					found[6] = true;
				}
				if (hrdw.Hop6().equals(nlyte.get(j).HostName())) {
					found[7] = true;
				}
				if (hrdw.Hop7().equals(nlyte.get(j).HostName())) {
					found[8] = true;
				}
				if (hrdw.Hop8().equals(nlyte.get(j).HostName())) {
					found[9] = true;
				}

			}
			// Adding to List
			addingList(found);
			found = setFalse(found);
		}
	}

	private static void addingList(boolean[] found) {
		//String[] sb = new String[4];
		for (int i = 0; i != found.length; ++i) {
			if (found[0] == true) {
				
			}
			if (found[1] == true) {

			}
			if (found[2] == true) {

			}
			if (found[3] == true) {

			}
			if (found[4] == true) {

			}
			if (found[5] == true) {

			}
			if (found[6] == true) {

			}
			if (found[7] == true) {

			}
			if (found[8] == true) {

			}
			if (found[9] == true) {

			}
		}

	}

	private static boolean[] setFalse(boolean[] v) {
		for (int i = 0; i != v.length; i++) {
			v[i] = false;
		}
		return v;
	}

	// Adding Serial Numbers from Asset Tags
	// @SuppressWarnings("unused")
	@SuppressWarnings("unused")
	private static Sheet addSerial(Sheet nlyte, String serial) {
		nlyte.setSerial(serial);
		return nlyte;
	}

	// Adding Asset Tags from Serial Numbers
	@SuppressWarnings("unused")
	private static Sheet addTag(Sheet nlyte, String tag) {

		nlyte.setAssetTag(tag);
		return nlyte;
	}

}
