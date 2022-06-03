package amazOrgan.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import amazOrgan.pojos.Antibody;
import amazOrgan.pojos.Antigen;
import amazOrgan.pojos.Doctor;
import amazOrgan.pojos.Donor;
import amazOrgan.pojos.Location;
import amazOrgan.pojos.Organ;
import amazOrgan.pojos.Receptor;
import amazOrgan.pojos.Request;
import amazOrgan.pojos.Type_organ;

public class Utilities {

	public static Receptor addreceptormenu() {

		Receptor receptor = null;

		System.out.println("Insert the next value:");
		Integer dni = readPositiveIntFromKeyboard("DNI");
		LocalDate dob = readDateFromKeyboard("DOB: yyyy-MM-dd");
		String status = askStatus("STATUS");
		String blood_type = askBloodType();
		Boolean alive = readBooleanFromKeyboard("ALIVE");
		Integer urgency = readIntFromKeyboardInRange("URGENCY", 1, 5);
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard();
		Request request = readRequestFromKeyboard("REQUEST:");

		receptor = new Receptor(dni, dob, status, blood_type, alive, urgency, antigen, antibody, location, request);

		return receptor;

//**********		//TODO call match function
	}
//**********		// call match function

	public static Receptor updateReceptorMenu(Receptor r) {
		// TODO
		System.out.println("YOU CAN ONLY CHANGE STATUS, URGENCY, URGENCY");
		String newstatus = askStatus("UPDATE STATUS");
		Boolean newalive = readBooleanFromKeyboard("UPDATE ALIVE");
		Integer newurgency = readIntFromKeyboardInRange("UPDATE URGENCY", 1, 5);

		Receptor newreceptor = new Receptor(r.getDni(), r.getDob(), newstatus, r.getBlood_type(), newalive, newurgency,
				r.getAntigen(), r.getAntibody(), r.getLocation(), r.getRequest());

		return newreceptor;
	}

	static String askBloodType() {
		try {
			Integer option;
			while (true) {
				String a = "A+";
				String b = "B+";
				String o = "O+";
				String ab = "AB+";
				String x = "A-";
				String y = "B-";
				String z = "O-";
				String xy = "AB-";
				System.out.println("INSERT THE NUMBER OF THE BLOODTYPE YOU WANT");
				System.out.println("A+    1");
				System.out.println("B+    2");
				System.out.println("AB+   3");
				System.out.println("O+    4");
				System.out.println("A-    5");
				System.out.println("B-    6");
				System.out.println("AB-   7");
				System.out.println("O-    8");

				option = readIntFromKeyboard("INSERT THE NUMBER");
				switch (option) {

				case 1:
					return a;

				case 2:
					return b;

				case 3:
					return o;

				case 4:
					return ab;

				case 5:
					return x;
				case 6:
					return y;

				case 7:
					return z;

				case 8:
					return xy;

				default:
					System.out.println("The option is not correct");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static Request readRequestFromKeyboard(String question) {
		// TODO test method
		System.out.println(question + "");
		Type_organ typeOfOrgan = new Type_organ(askTypeOfOrgan("ORGANS"));
		Request request = new Request(typeOfOrgan, readPositiveFloatFromKeyboard("organ size of the organ needed2"),
				readBooleanFromKeyboard("organ received"), null);
		return request;
	}

	public static String askTypeOfOrgan(String question) {
		// works
		try {
			Integer option;
			while (true) {
				String kidney = "kidney";
				String liver = "liver";
				String pancreas = "pancreas";
				String lung = "lung";
				String heart = "heart";
				String bowel = "bowel";
				String boneMarrow = "bone marrow";

				System.out.println("INSERT THE ORGAN THE PATIENT NEEDS");
				System.out.println("KIDNEY       1");
				System.out.println("LIVER        2");
				System.out.println("PANCREAS     3");
				System.out.println("LUNG         4");
				System.out.println("HEART        5");
				System.out.println("BOWEL        6");
				System.out.println("BONE MARROW  7");

				option = readIntFromKeyboard("INSERT THE NUMBER");
				switch (option) {

				case 1:
					return kidney;

				case 2:
					return liver;

				case 3:
					return pancreas;

				case 4:
					return lung;

				case 5:
					return heart;

				case 6:
					return bowel;

				case 7:
					return boneMarrow;

				default:
					System.out.println("The option is not correct");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	public static String askStatus(String question) {
		// WORKS
		try {
			Integer option;

			while (true) {
				String accepted = "Accepted";
				String rejected = "Rejected";
				String waiting = "Waiting";
				String operating = "Operating";
				System.out.println("CHOOSE STATUS");
				System.out.println("(YOU CAN ONLY CHOOSE: ACCEPTED, REJECTED, WAITING, OPERATING)");
				System.out.println("OPTION 1: ACCEPTED ");
				System.out.println("OPTION 2: REJECTED");
				System.out.println("OPTION 3: WAITING");
				System.out.println("OPTION 4: OPERATING");
				option = readIntFromKeyboard("INSERT THE NUMBER");
				switch (option) {

				case 1:
					return accepted;

				case 2:
					return rejected;

				case 3:
					return waiting;

				case 4:
					return operating;

				default:
					System.out.println("The option is not correct");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	// TODO test method
	public static Location readLocationFromKeyboard() {

		Float latitude = readFloatFromKeyboard("Introduce the latitude.");
		Float longitude = readFloatFromKeyboard("Introduce the longitude.");

		Location location = new Location(latitude, longitude);
		return location;
	}

	public static int readIntFromKeyboard(String question) {
		System.out.println(question);
		Integer num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);
				return num;

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read Positive Integer
	public static Integer readPositiveIntFromKeyboard(String question) {
		System.out.println(question);
		Integer num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);

				if (num > 0) {
					return num;
				} else {
					System.out.println("Enter a positive number.");
				}

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number.");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read Integer in range
	public static Integer readIntFromKeyboardInRange(String question, int begin, int end) {
		System.out.println(question);
		Integer num;
		if (begin > end) {
			Integer temp = begin;
			begin = end;
			end = begin;
		}
		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Integer.parseInt(s);
				if (num >= begin && num <= end) {
					return num;
				} else {
					System.out.println("The number must be between " + begin + " and " + end + ".");
				}
			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read String
	public static String readStringFromKeyboard(String question) {
		System.out.println(question);

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();

				return s;

			} catch (IOException ioe) {
				System.out.println("Error. Enter a new text string.");
			}
		}
	}

	// Read Float
	public static float readFloatFromKeyboard(String question) {
		System.out.println(question);
		Float num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Float.parseFloat(s);
				return num;

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter a number.");
			}
		}
	}

	// Read Boolean
	public static Boolean readBooleanFromKeyboard(String question) {
		System.out.println(question + " (yes or no)");

		Boolean b;
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String r = null;
				r = bf.readLine();
				if (r.equals("yes")) {
					return true;
				} else if (r.equals("no")) {
					return false;
				} else {
					System.out.println("Error, introduce 'yes' or 'no'.");
				}

			} catch (IOException ioe) {
				System.out.println("Error");
			}
		}

	}

	// Read a date
	public static LocalDate readDateFromKeyboard(String question) {
		System.out.println(question);
		// Enter a date -> String
		String dateString = null;

		while (true) {
			try {
				dateString = readStringFromKeyboard("");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(dateString, formatter);
				return date;

			} catch (Exception e) {
				System.out.println("Error. Re-enter a date in this format (yyyy-MM-dd)");
			}
		}
	}

	// Read antigen
	public static Antigen readAntigenFromKeyboard() {

		Boolean a = readBooleanFromKeyboard("Is the antigen A present?");
		Boolean b = readBooleanFromKeyboard("Is the antigen B present?");
		Boolean c = readBooleanFromKeyboard("Is the antigen C present?");
		Boolean dp = readBooleanFromKeyboard("Is the antigen DP present?");
		Boolean dq = readBooleanFromKeyboard("Is the antigen DQ present?");
		Boolean dr = readBooleanFromKeyboard("Is the antigen DR present?");

		Antigen antigen = new Antigen(a, b, c, dp, dq, dr);
		return antigen;
	}

	// Read antibody
	public static Antibody readAntibodyFromKeyboard() {

		Boolean class_I = readBooleanFromKeyboard("Is the antibody class_I present?");
		Boolean class_II = readBooleanFromKeyboard("Is the antibody class_II present?");

		Antibody antibody = new Antibody(class_I, class_II);
		return antibody;
	}

	// Read location
	public static Location readLocationFromKeyboard(String question) {

		System.out.println(question);
		Float latitude = readFloatFromKeyboard("Introduce the latitude.");
		Float longitude = readFloatFromKeyboard("Introduce the longitude.");

		Location location = new Location(latitude, longitude);
		return location;
	}

	public static Float readPositiveFloatFromKeyboard(String question) {
		System.out.println(question);
		Float num;

		while (true) {
			try {
				String s = null;
				BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
				s = bf.readLine();
				num = Float.parseFloat(s);

				if (num > 0) {
					return num;
				} else {
					System.out.println("Enter a positive number.");
				}

			} catch (IOException ioe) {
				System.out.println("Error. Re-enter a number.");
			} catch (NumberFormatException nfe) {
				System.out.println("You have not entered a number. Enter an integer number.");
			}
		}
	}

	// Read a death donor
	// TODO method
	public static Donor readDonorFromKeyboard(Doctor doctor, String question) {
		System.out.println(question);

		Donor donor = null;
		Integer dni = readPositiveIntFromKeyboard("Enter the dni.");
		LocalDate dob = readDateFromKeyboard("Enter the date of birth.");
		Boolean alive = false;
		String bloodType = askBloodType();
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard("Enter the location.");
		Doctor doctor_charge = doctor;
		// List<Organ> organs = readListOrgansFromKeyboard();

		donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge, organs);
		return donor;
	}

	// Update a donor
	// TODO test method
	public static Donor readDonortoUpdate(Donor d) {

		Donor donor = null;
		Integer dni = d.getdni();
		LocalDate dob = d.getdob();
		Boolean alive = false;
		String bloodType = askBloodType();
		Antigen antigen = readAntigenFromKeyboard();
		Antibody antibody = readAntibodyFromKeyboard();
		Location location = readLocationFromKeyboard("Enter the location.");
		Doctor doctor_charge = d.getDoctor_charge();
		List<Organ> listOrgans = d.getOrgans();

		donor = new Donor(dni, dob, alive, bloodType, antigen, antibody, location, doctor_charge, listOrgans);
		return donor;
	}

	public static void main(String[] ars) {
		LocalDate date = readDateFromKeyboard("Enter a date");
	}

	// TODO readListOrgansFromKeyboard()
	public static List readListOrgansFromKeyboard() {try {
		Integer option = -1;
	}
		}
}
