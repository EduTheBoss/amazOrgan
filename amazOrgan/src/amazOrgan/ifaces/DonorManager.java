package amazOrgan.ifaces;

import java.util.List;

import amazOrgan.pojos.Donor;

public interface DonorManager {

	/*addDonor (d: Donor): void 

	deleteDonor(DNI: int): void 

	getDonor( DNI: int): void  

	updateAlive (DNI: int): void 

	showDonorsByBloodType (bloodtype: text): void 
*/
	
	public void addDonor(Donor d);
	public void deleteDonor(Integer DNI);
	public void getDonor(Integer DNI);
	public void updateAlive (Integer DNI);
	public List <Donor> showDonorsByBloodType (String bloodType);
	
	
}
