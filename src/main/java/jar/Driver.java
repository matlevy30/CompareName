package main.java.jar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static ArrayList<UpdateSheet> missingNames;

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
		// If tag was found or not
		boolean[] found = { false, false, false, false, false, false, false, false, false, false };
		for (int i = 0; i != hrdwr.size(); ++i) {
			HardwareSheet hrdw = (HardwareSheet) hrdwr.get(i);
			for (int j = 0; j != nlyte.size(); ++j) {
				// If the name is not found add it to the list
		
				
				if(hrdw.HostName1().equals(nlyte.get(j).HostName())) {
					found[0] = true;
				}
				if(hrdw.HostName2().equals(nlyte.get(j).HostName())) {
					found[1] = true;
				}
				if(hrdw.Hop1().equals(nlyte.get(i).HostName())) {
					found[2] = true;
				}
				if(hrdw.Hop2().equals(nlyte.get(i).HostName())) {
					found[3] = true;
				}
				if(hrdw.Hop3().equals(nlyte.get(i).HostName())) {
					found[4] = true;
				}
				if(hrdw.Hop4().equals(nlyte.get(i).HostName())) {
					found[5] = true;
				}
				if(hrdw.Hop5().equals(nlyte.get(i).HostName())) {
					found[6] = true;
				}
				if(hrdw.Hop6().equals(nlyte.get(i).HostName())) {
					found[7] = true;
				}
				if(hrdw.Hop7().equals(nlyte.get(i).HostName())) {
					found[8] = true;
				}
				if(hrdw.Hop8().equals(nlyte.get(i).HostName())) {
					found[9] = true;
				}
			}
			// Adding to List
			List<UpdateSheet> values = addingList(found,hrdw);
			boolean f = false;
			for(int z = 0; z != values.size(); ++z) {
				for(int k = 0; k != missingNames.size(); ++k) {
					if(values.get(z).HostName().equals(missingNames.get(k).HostName())) {
						f = true;
					}
				}
				if(!f) {
					missingNames.add(values.get(z));
				}
				f = false;
			}
			
			found = setFalse(found);
		}
	}

	private static List<UpdateSheet> addingList(boolean[] found , HardwareSheet hrdw) {
		//String[] sb = new String[4];
		String[] values = new String[4];
		List<UpdateSheet> update = new ArrayList<>();
		for (int i = 0; i != found.length; ++i) {
			if (found[0] == false) {
				if(hrdw.HostName1().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.HostName1();
				//System.out.print(values[0].length());
				values[1] = hrdw.Cabinate1();
				//System.out.println(" " + values[1] + " " + values[1]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
				
			}
			if (found[1] == false) {
				if(hrdw.HostName2().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.HostName2();
				values[1] = hrdw.Cabinate2();
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));

			}
			if (found[2] == false) {
				if(hrdw.Hop1().equals("BLANK") || hrdw.Hop1().equals("DIRECT")) {
					continue;
				}
				values[0] = hrdw.Hop1();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
			}
			if (found[3] == false) {
				if(hrdw.Hop2().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop2();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
			}
			if (found[4] == false) {
				if(hrdw.Hop3().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop3();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));

			}
			if (found[5] == false) {
				if(hrdw.Hop4().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop4();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));

			}
			if (found[6] == false) {
				if(hrdw.Hop5().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop5();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));

			}
			if (found[7] == false) {
				if(hrdw.Hop6().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop6();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
			}
			if (found[8] == false) {
				if(hrdw.Hop7().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop7();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
			}
			if (found[9] == true) {
				if(hrdw.Hop8().equals("BLANK")) {
					continue;
				}
				values[0] = hrdw.Hop8();
				values[1] = hrdw.Cabinate(values[0]);
				values[2] = hrdw.Pod(values[1]);
				values[3] = hrdw.Row(values[1]);
				update.add(new UpdateSheet(values));
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
