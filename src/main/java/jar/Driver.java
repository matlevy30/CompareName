package main.java.jar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	private static final String[] hostNameHeader = { "Name", "Cabinet", "Pod", "Row" };
	private static final String[] cabinateHeader = { "Name", "Cabinet Nlyte", "Cabinet Hardware", "Pod", "Row" };
	public static ArrayList<UpdateSheet> missingNames;
	public static ArrayList<UpdateSheet> cabinates;

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

		// No match Cab
		cabinates = new ArrayList<>();

		// =======================================================================
		// Comparing Asset Tag Info for both Nlyte -> UAPM
		compareName(NlyteInfo, HardwareInfo);
		// =======================================================================
		// Creating CSV files all of the lists
		WriteCSV write = new WriteCSV(hostNameHeader, missingNames, "Missing.csv");
		write.wirte();

		write = new WriteCSV(cabinateHeader, cabinates, "Cabinate.csv");
		write.wirte();
	}

	// Comparing Tags
	public static void compareName(ArrayList<Sheet> nlyte, ArrayList<Sheet> hrdwr) {
		// If tag was found or not
		boolean[] found = { false, false, false, false, false, false, false, false, false, false };
		String[] cabValues = new String[5];
		List<UpdateSheet> cabs = new ArrayList<>();
		for (int i = 0; i != hrdwr.size(); ++i) {
			HardwareSheet hrdw = (HardwareSheet) hrdwr.get(i);
			for (int j = 0; j != nlyte.size(); ++j) {
				// If the name is not found add it to the list

				if (hrdw.HostName1().equals(nlyte.get(j).HostName())) {
					found[0] = true;
					if (!(hrdw.Cabinate1().equals(nlyte.get(j).cabinateName()))) {
						cabValues[0] = nlyte.get(j).HostName();
						cabValues[1] = nlyte.get(j).cabinateName();
						cabValues[2] = hrdw.Cabinate1();
						cabValues[3] = hrdw.Pod(cabValues[2]);
						cabValues[4] = hrdw.Row(cabValues[2]);
						cabs.add(new UpdateSheet(cabValues));
						cabValues = new String[5];
						
					}
				}
				if (hrdw.HostName2().equals(nlyte.get(j).HostName())) {
					found[1] = true;
					if (!(hrdw.Cabinate2().equals(nlyte.get(j).cabinateName()))) {
						cabValues[0] = nlyte.get(j).HostName();
						cabValues[1] = nlyte.get(j).cabinateName();
						cabValues[2] = hrdw.Cabinate2();
						cabValues[3] = hrdw.Pod(cabValues[2]);
						cabValues[4] = hrdw.Row(cabValues[2]);
						cabs.add(new UpdateSheet(cabValues));
						cabValues = new String[5];
					}
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
			List<UpdateSheet> values = addingList(found, hrdw);
			addWithoutDuplicates(values, missingNames);
			addWithoutDuplicates(cabs, cabinates);
			found = setFalse(found);
		}
	}

	private static void addWithoutDuplicates(List<UpdateSheet> values , ArrayList<UpdateSheet> list) {
		boolean f = false;
		for (int z = 0; z != values.size(); ++z) {
			for (int k = 0; k != list.size(); ++k) {
				if (values.get(z).HostName().equals(list.get(k).HostName())) {
					f = true;
				}
			}
			if (!f) {
				list.add(values.get(z));
			}
			f = false;
		}
	}

	private static List<UpdateSheet> addingList(boolean[] found, HardwareSheet hrdw) {
		// String[] sb = new String[4];
		String[] values = new String[4];
		List<UpdateSheet> update = new ArrayList<>();
		if (found[0] == false) {
			if (!hrdw.HostName1().equals("-") && !hrdw.HostName1().contains("PORT")) {
				values[0] = hrdw.HostName1();
				// System.out.print(values[0].length());
				values[1] = hrdw.Cabinate1();
				// System.out.println(" " + values[1] + " " + values[1]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[1] == false) {
			if (!hrdw.HostName2().equals("-") && !hrdw.HostName2().contains("PORT")) {
				values[0] = hrdw.HostName2();
				values[1] = hrdw.Cabinate2();
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[2] == false) {
			if (!(hrdw.Hop1().equals("-")) && !(hrdw.Hop1().equals("DIRECT")) && !(hrdw.Hop1().contains("PORT"))) {
				values[0] = hrdw.Hop1();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[3] == false) {
			if (!hrdw.Hop2().equals("-") && !hrdw.Hop2().contains("PORT")) {
				values[0] = hrdw.Hop2();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[4] == false) {
			if (!hrdw.Hop3().equals("-") && !hrdw.Hop3().contains("PORT")) {
				values[0] = hrdw.Hop3();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[5] == false) {
			if (!hrdw.Hop4().equals("-") && !hrdw.Hop4().contains("PORT")) {
				values[0] = hrdw.Hop4();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[6] == false) {
			if (!hrdw.Hop5().equals("-") && !hrdw.Hop5().contains("PORT")) {
				values[0] = hrdw.Hop5();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[7] == false) {
			if (!hrdw.Hop6().equals("-") && !hrdw.Hop6().contains("PORT")) {
				values[0] = hrdw.Hop6();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[8] == false) {
			if (!hrdw.Hop7().equals("-") && !hrdw.Hop7().contains("PORT")) {
				values[0] = hrdw.Hop7();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		if (found[9] == false) {
			if (!hrdw.Hop8().equals("-") && !hrdw.Hop8().contains("PORT")) {
				values[0] = hrdw.Hop8();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				values = new String[4];
			}

		}
		return update;
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
